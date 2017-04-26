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
                    url: "/api/shortUrls?size=5&page=" + (pageNumber - 1),
                    type: "GET",
                    dataType: "json"
                })
                    .done(function (data) {
                        counter.set('page', data);
                    });
            }
        },
        nextPage: function (event) {
            var pageNumber = parseInt(counter.get('page').number);
            if (pageNumber < (parseInt(counter.get('page').totalPages) - 1)) {
                $.ajax({
                    url: "/api/shortUrls?size=5&page=" + (pageNumber + 1),
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
            customUrl: false,
            idUnavailable: false,
            item: []
        }
    });

    createUrl.on({
        newUrl: function (event) {
            createUrl.set('invalidUrl', false);
            createUrl.set('errorMessage', false);
            var node = this.el.querySelector(".input-lg");
            var nodeCustom = this.el.querySelector("#customId");
            var customId = nodeCustom.value.trim();

            var urlValue = node.value.trim();
            var body = {url: urlValue};
            if (!!customId) {
                body.customStringId = customId;
            }
            $.ajax({
                url: "/api/shortUrls",
                type: "POST",
                data: JSON.stringify(body),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            })
                .done(function (data) {
                    urlList.unshift('items', data);
                })
                .error(function (data) {
                    console.log(data.responseText);
                    var response = JSON.parse(data.responseText);

                    createUrl.set('errorMessage', response.message);
                });
            node.value = '';
            nodeCustom.value = '';
        },
        setCustom: function (event) {
            console.log(event.node);
            console.log($(event.node).getAttribute("checked"));

        }
    });
});

