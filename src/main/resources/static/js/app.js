// INSPINIA Landing Page Custom scripts
$(document).ready(function () {
    var urlList = new Ractive({
        el: '#urls',

        template: '#main',

        data: {
            items: []
        }
    });
    urlList.on({
        copyToClipboard: function (event) {
            console.log(event);
            var item = this.get(event.keypath);
            var text = item.shortUrl;
            if (window.clipboardData && window.clipboardData.setData) {
                // IE specific code path to prevent textarea being shown while dialog is visible.
                return clipboardData.setData("Text", text);

            } else if (document.queryCommandSupported && document.queryCommandSupported("copy")) {
                var textarea = document.createElement("textarea");
                textarea.textContent = text;
                textarea.style.position = "fixed";  // Prevent scrolling to bottom of page in MS Edge.
                document.body.appendChild(textarea);
                textarea.select();
                try {
                    return document.execCommand("copy");  // Security exception may be thrown by some browsers.
                } catch (ex) {
                    console.warn("Copy to clipboard failed.", ex);
                    return false;
                } finally {
                    document.body.removeChild(textarea);
                }
            }
        }
    });

    var counter = new Ractive({
        el: '#counterDiv',

        template: '#counters',

        data: {
            page: {
                number: 0
            },
            counters: {
                "totalLinks": 0,
                "totalHits": null
            }
        }
    });
    setInterval(totalUpdate, 1000); //300000 MS == 5 minutes

    counter.on({
        loadUrls: function (event) {
            $.ajax({
                url: "/api/shortUrls?size=5&" + counter.get("page.number"),
                type: "GET",
                dataType: "json"
            })
                .done(function (data) {
                    counter.set('page', data);
                });
        },
        prevPage: function (event) {
            var pageNumber = parseInt(counter.get('page').number);
            if (pageNumber > 0) {
                $.ajax({
                    url: "/api/shortUrls?size=5&page=" + (pageNumber-1),
                    type: "GET",
                    dataType: "json"
                })
                    .done(function (data) {
                        counter.set('page', data);
                    });
            }
        },
        nextPage : function (event) {
            var pageNumber = parseInt(counter.get('page').number);
            if (pageNumber < (parseInt(counter.get('page').totalPages)-1)) {
                $.ajax({
                    url: "/api/shortUrls?size=5&page=" + (pageNumber+1),
                    type: "GET",
                    dataType: "json"
                })
                    .done(function (data) {
                        counter.set('page', data);
                    });
            }

        }
    });

    function totalUpdate() {
        $.ajax({
            url: "/api/shortUrls/total",
            type: "GET",
            dataType: "json"
        })
            .done(function (data) {
                counter.set('counters', data);
            });
    }

    var createUrl = new Ractive({
        el: '#create',

        template: '#form',

        data: {
            item: []
        }
    });

    createUrl.on({
        newUrl: function (event) {
            createUrl.set('invalidUrl', false);
            var node = this.el.querySelector(".input-lg");
            var urlValue = node.value.trim();
            if (!!urlValue) {
                var urlRegex = '^(?!mailto:)(?:(?:http|https|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?:(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[0-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))|localhost)(?::\\d{2,5})?(?:(/|\\?|#)[^\\s]*)?$';
                var re = new RegExp(urlRegex);
                if (re.test(urlValue)) {
                    $.ajax({
                        url: "/api/shortUrls",
                        type: "POST",
                        data: JSON.stringify({url: urlValue}),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json"
                    })
                        .done(function (data) {
                            urlList.unshift('items', data);
                        });
                    node.value = '';
                } else {
                    createUrl.set('invalidUrl', true);
                }
            } else {
                createUrl.set('invalidUrl', true);
            }
        }
    });
});

