// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require jquery.print
//= require moment.min
//= require jquery-ui.min
//= require SmoothScroll
//= require bootstrap.min
//= require bootstrap-datepicker
//= require bootstrapValidator.min
//= require combodate
//= require select2.min
//= require sweetalert2.min
//= require core
//= require main
//= require country
//= require donetyping
//= require jquery-migrate.min
//= require jquery-ui-sliderAccess
//= require jquery.fancybox.pack
//= require livicons-1.4.min
//= require loadimg.min
//= require spin.min
//= require raphael.min
//= require jquery.multi-select

//= require jquery.carouFredSel-6.2.1-packed
//= require video

//= require_tree views
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });
    })(jQuery);
}
