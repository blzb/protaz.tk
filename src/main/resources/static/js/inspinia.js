// INSPINIA Landing Page Custom scripts
$(document).ready(function () {
    var urlList = new Ractive({
        el: '#urls',

        template: '#main',

        data: {
            items: []
        }
    });

    var counter = new Ractive({
        el: '#counterDiv',

        template: '#counters',

        data: {
            counters:{
                "totalLinks": 0,
                "totalHits": null
            }
        }
    });
    setInterval(totalUpdate, 1000); //300000 MS == 5 minutes

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
            var node = this.el.querySelector(".input-lg");
            var urlValue = node.value.trim();
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
        }
    });

});

