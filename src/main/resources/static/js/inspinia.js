// INSPINIA Landing Page Custom scripts
$(document).ready(function () {
    var todoList = new Ractive({
        // Specify a target element - an ID, a CSS selector, or the element itself
        el: '#urls',

        // Specify a template, or the ID of a script tag containing the template
        template: '#main',

        // This is our viewmodel - as well as our list of tasks (which gets added
        // later from localStorage - see persistence.js), it includes helper
        // functions and computed values
        data: {
            items:[
                {shortUrl: 'dfldsfkd.com'},
                {shortUrl: 'lkds.com'}
            ]
        }
    });
});

