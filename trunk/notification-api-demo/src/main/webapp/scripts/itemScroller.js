/*
 * 	itemScroller 0.1 - jQuery plugin
 *	written by Lasith Gunawardana (lasith12@gmail.com)
 *
 *	Copyright (c) 2011 Lasith Gunawardana
 *	Dual licensed under the MIT (MIT-LICENSE.txt)
 *	and GPL (GPL-LICENSE.txt) licenses.
 *
 *	Built for Ceybid 
 *	http://ceybid.com
 *
 */

(function ($) {
    $.fn.itemScroller = function (options) {

        var defaults = {
            container:'.container',
            slides:'.slides',
            items:'.items',
            ajx_url:'',
            ajx_data:'',
            ajx_loader:'.loader'
        };

        this.each(function () {

            var $this = $(this);
            var o = $.extend(defaults, options);
            var contentWidth = $(o.container, $this).width();
            var itemBoxWidth = $(o.items, $this).width();
            var itemSum = $(o.slides, $this).children().size();
            var sliderWidth = itemBoxWidth * itemSum;
            var nextCount = 0;
            var prevCount = 0;
            var itemOffset = 5;
            var slideTrigger = 0;
            var sliderPosition = 0;

            $(o.slides, $this).css({'width':sliderWidth + (22 * itemSum) + 200});

            function animateItems() {

                //window.alert(itemSum + ' ' + nextCount );
                if (nextCount >= itemSum) {
                    //slideTrigger = 0;
                    //nextCount = 0;

                    $(o.ajx_loader, $this).show();

                    $.ajax({
                        type:"POST",
                        url:o.ajx_url,
                        data:o.ajx_data,
                        complete:function (data) {
                            //window.alert(data.responseText);
                            $(o.slides, $this).append(data.responseText);
                            itemSum = $(o.slides, $this).children().size();
                            sliderWidth = itemBoxWidth * itemSum;
                            $(o.slides, $this).css({'width':sliderWidth + (22 * itemSum) + 200});
                            $(o.ajx_loader, $this).hide();
                        }
                    });

                }

                sliderPosition = (slideTrigger * ((itemBoxWidth * itemOffset) + (22 * itemOffset))) + (slideTrigger * 10);
                //window.alert(sliderPosition + ' ' + slideTrigger );

                $(o.slides, $this).animate({
                    left:-sliderPosition
                }, 500);

            }

            ;

            $(".next", $this).click(function () {

                nextCount += itemOffset;
                slideTrigger++;
                animateItems();
                return false;

            });

            $(".previous", $this).click(function () {

                if (slideTrigger == 0)return;
                nextCount -= itemOffset;
                slideTrigger--;
                animateItems();
                return false;

            });

        });

    }

})(jQuery);