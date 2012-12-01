<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>
        <fmt:message key="welcome.title"/>
    </title>

    <script type="text/javascript">
        <!--
        $(document).ready(function () {

            /* $(".item-wrapper").itemScroller({
             ajx_url: 'data.html'
             }); */
            $(".item-wrapper").loopedCarousel();

            setItemDesc = function () {
                var time = $(".cdeck-active img").attr("alt");
                var iname = $(".cdeck-active img").attr("name");
                var bid = $(".cdeck-active img").attr("title");

                $(".act-count").html(time);
                $(".act-bid").html('Last Bid Rs.' + bid + '/=');
                $(".act-desc").html(iname);
            }


            var bnrWidth = $("#cdlist").width();
            var imgFrame = $(".cdeck").width();
            var screenSum = $("#reel div").size() - 1;
            var reelWidth = imgFrame * screenSum;
            var $firstActive = $('#reel div:first').next();
            $firstActive.removeClass('cdeck');
            $firstActive.addClass('cdeck-active');
            //window.alert(imgFrame);
            setItemDesc();

            $("#reel").css({'width':reelWidth + 400});

            var triggerID = 1;


            rotate = function () {
                var $active = $(".cdeck-active");

                if (triggerID == (screenSum)) {
                    $active = $('#reel div:first');
                    $active.removeClass('cdeck');
                    $active.addClass('cdeck-active');
                }

                if (triggerID == screenSum)triggerID = 0;
                var image_reelPosition = (triggerID * imgFrame) + (triggerID * 4);
                //window.alert(image_reelPosition + ' ' +triggerID );

                $("#reel").animate({
                    left:-image_reelPosition
                }, 500);

                $active.animate({height:75, width:75}, 500);
                $active.removeClass('cdeck-active');
                $active.addClass('cdeck');

                $active.next().animate({
                    height:110, width:130
                }, 500, function () {

                    $active.next().removeClass('cdeck');
                    $active.next().addClass('cdeck-active');

                    setItemDesc();

                });


                triggerID++;

            };

            rotateSwitch = function () {
                play = setInterval('rotate()', 3000);
            };

            rotateSwitch();

            $(".act-desc").hover(function () {
                clearInterval(play);
            }, function () {
                rotateSwitch();
            });

            var posi, subPanel;

            $(".cat-link").hover(function () {
                posi = $(this).position();
                subPanel = $(this).next(".sub-category-box");
                subPanel.css({'top':posi.top - 11, 'left':posi.left + 158}).fadeIn();
            }, function () {
                subPanel.hide();
            });


            $(".sub-category-box").hover(function () {
                $(this).show();
            }, function () {
                $(this).fadeOut();
            });

        });
        //-->
    </script>
</head>

<!-- Start Container -->
<div id="container">

<!-- Start content area 1 -->
<div class="content-area-1">

<!-- Categories -->
<div class="category-box">
    <ul>
        <li><a class="cat-link" href="#">All Categories</a>

            <div class="sub-category-box">
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
            </div>
        </li>
        <li><a class="cat-link" href="#">Antiques</a>

            <div class="sub-category-box">
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
            </div>
        </li>
        <li><a class="cat-link" href="#">Fashion</a>

            <div class="sub-category-box">
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
                <div class="sub-category-box-items"><a href="#">Antiques</a></div>
            </div>
        </li>
        <li><a class="cat-link" href="#">Cars, Bikes and Vehicles</a></li>
        <li><a class="cat-link" href="#">Consumer Electronics</a></li>
        <li><a class="cat-link" href="#">Health & Fitness</a></li>
        <li><a class="cat-link" href="#">Jewellery & Watches</a></li>
        <li><a class="cat-link" href="#">Popularity Contest</a></li>
        <li><a class="cat-link" href="#">Other</a></li>
    </ul>
</div>

<!-- Item scroller -->
<div class="latest-box">
    <div class="titles">
        Active Bids
    </div>

    <div class="clear"></div>

    <div id="cdlist">
        <div id="reel">
            <div align="center" class="cdeck">
                <img src="images/1.jpg" alt="picture">
            </div>

            <div align="center" class="cdeck-active">
                <img src="images/2.jpg" title="4500" alt="6:23:45" name="Item description for pic 1">
            </div>

            <div align="center" class="cdeck">
                <img src="images/3.jpg" title="6600" alt="7:12:42" name="Item description for pic 2">
            </div>

            <div align="center" class="cdeck">
                <img src="images/4.jpg" title="7674" alt="2:55:34" name="Item description for pic 3">
            </div>

            <div align="center" class="cdeck">
                <img src="images/5.jpg" title="5665" alt="2:34:54" name="Item description for pic 4">
            </div>

            <div align="center" class="cdeck">
                <img src="images/3.jpg" title="6600" alt="7:12:42" name="Item description for pic 2">
            </div>

            <div align="center" class="cdeck">
                <img src="images/4.jpg" title="7674" alt="2:55:34" name="Item description for pic 3">
            </div>

            <div align="center" class="cdeck">
                <img src="images/5.jpg" title="5665" alt="2:34:54" name="Item description for pic 4">
            </div>
        </div>

        <div class="dwn-arrw"></div>
    </div>

    <div class="clear"></div>

    <div id="act-content">
        <div class="act-count">8:03:48</div>
        <div class="act-bid">Last Bid Rs.5200/=</div>
        <div class="clear"></div>
        <div class="act-desc">Adidas men's running shoes Sandrest</div>
    </div>

</div>

<!-- Inner page banners -->
<div class="banner-box"><img src="images/banner1.jpg"/></div>
<div class="banner-box2"><img src="images/banner2.jpg"/></div>

<div class="clear"></div>

<!-- Main item listing areas -->
<!-- Quick bye -->
<div class="item-box">
    <div class="titles">
        Quick Buy
    </div>

    <div class="clear"></div>

    <div class="item-wrapper">
        <div class="next"></div>
        <div class="previous"></div>
        <div class="loader"></div>

        <div class="container">
            <div class="slides">
                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Sold Items -->
<div class="item-box">
    <div class="titles">
        Sold Items
    </div>

    <div class="clear"></div>

    <div class="item-wrapper">
        <div class="next"></div>
        <div class="previous"></div>
        <div class="loader"></div>

        <div class="container">
            <div class="slides">
                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

                <div class="items">
                    <dt></dt>
                    <dd><a href="#">Item name for first item box</a></dd>
                    <dl>Rs.1500/=</dl>
                </div>

            </div>
        </div>
    </div>
</div>
</div>
<!-- End content area 1 -->

<!-- Start content area 2 -->
<div class="content-area-2">
    <div class="right-col">

    </div>
</div>
<!-- End content area 2 -->

<div class="clear"></div>

</div>
<!-- End Container -->

<div class="clear"></div>