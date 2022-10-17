$( document ).ready(function() {
    $('.dropdown').each(function (key, dropdown) {
        let $dropdown = $(dropdown);
        $dropdown.find('.dropdown-menu a').on('click', function () {
            $dropdown.find('button').text($(this).text()).append(' <span class="caret"></span>');
        });
    });
});