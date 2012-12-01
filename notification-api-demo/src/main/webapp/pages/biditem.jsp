<head>
    <script type="text/javascript">
        <!--
        $(document).ready(function () {

            var cd_hr = $(".hr");
            var cd_min = $(".min");
            var cd_sec = $(".sec");


            setInterval(function () {

                cd_sec = parseInt($(".sec").text()) - 1;

                if (cd_sec == -1) {

                    cd_sec = 59;
                    cd_min = parseInt($(".min").text()) - 1;

                    if (cd_min == -1) {

                        cd_min = 59;
                        cd_hr = parseInt($(".hr").text()) - 1;
                    }
                }

                $(".sec").text(cd_sec);
                $(".min").text(cd_min);
                $(".hr").text(cd_hr);

            }, 1000);


            // tab controll

            function showtab(tab) {
                $(".tab-content").hide();
                $(tab).show();

                $("#t1").removeClass().addClass("tab");
                $("#t2").removeClass().addClass("tab");
                $("#t3").removeClass().addClass("tab");
            }


            // init
            showtab("#tab1");
            $("#t1").removeClass().addClass("tab-active");


            $("#t1").click(function () {
                showtab("#tab1");
                $(this).removeClass().addClass("tab-active");

            });

            $("#t2").click(function () {
                showtab("#tab2");
                $(this).removeClass().addClass("tab-active");
            });

            $("#t3").click(function () {
                showtab("#tab3");
                $(this).removeClass().addClass("tab-active");
            });


        });
        //-->
    </script>
</head>

<!-- Start Container -->
<div id="container">

    <!-- Start content area 1 -->
    <div class="content-area-1">


        <div class="item-data-box">
            <div class="item-tit">Adidas men's running shoes Sandrest</div>
            <div class="clear"></div>
            <div class="item-data-col1">
                <div class="img-box">
                    <img src="images/item.png"/>
                </div>
                <img src="images/item-thumb.jpg"/> <img
                    src="images/item-thumb.jpg"/> <img src="images/item-thumb.jpg"/>
                <img src="images/item-thumb.jpg"/>
            </div>
            <div class="item-data-col2">
                <div class="count-dwn-wrapper">
                    <div class="hr">30</div>
                    <div class="min">20</div>
                    <div class="sec">10</div>
                    <div class="bids">10</div>
                </div>
                <div class="clear"></div>
                <div class="price-box">
                    <span class="itm-price">Rs.5200.00 ($50) </span>
                    <span class="last-bid">Last Bid Rs. 4500.00 </span>
                </div>
                <img class="buy-btn" src="images/buy.jpg"/>

                <div class="clear"></div>

                <form action="" method="get">
                    <div class="price-box2">
                        <span class="itm-price">Quantity: 5 </span>
                        <span class="last-bid">Item condition: New</span>
                        <span class="last-bid">Your Bid: <input name="textfield" type="text" id="textfield" size="10"/></span>
                    </div>
                    <input class="bid-btn" name="" type="image" src="images/bid.jpg"/>
                </form>

            </div>
            <div class="item-data-col3">
                <div class="tit">Seller Information</div>
                <div class="member">bidnbuy</div>
                <img src="images/5-star.png"/>

                <div class="rating">100% Positive feedback</div>
                <img src="images/ask-btn.png"/>
            </div>
        </div>


        <div class="clear"></div>

        <div class="item-desc">
            <div class="clear"></div>

            <div class="tabs">
                <div id="t1" class="tab-active">
                    <a class="tab-text">Item Details</a>
                </div>
                <div id="t2" class="tab">
                    <a class="tab-text">Shipping Details</a>
                </div>
                <div id="t3" class="tab">
                    <a class="tab-text">Related Items</a>
                </div>
            </div>
            <div id="tab1" class="tab-content">
                <div class="title">Adidas men's running shoes 1</div>
                <div class="desc">
                    <p>
                        Easy care for your hair, SalonDry Compact , With 2 Years
                        Warranty............ Easy to use <br/>
                    </p>

                    <p>Easy storage hook Professional results</p>

                    <p>for careful drying</p>

                    <p>1400W for beautiful results</p>

                    <p>Narrow concentrator for focused airflow Technical specifications</p>

                    <p>Wattage: 1400 W Serviceability</p>

                    <p>Replacement</p>
                </div>
            </div>

            <div id="tab2" class="tab-content">
                <div class="title">Adidas men's running shoes 2</div>
                <div class="desc">
                    <p>
                        Easy care for your hair, SalonDry Compact , With 2 Years
                        Warranty............ Easy to use <br/>
                    </p>

                    <p>Easy storage hook Professional results</p>

                    <p>for careful drying</p>

                    <p>1400W for beautiful results</p>

                    <p>Narrow concentrator for focused airflow Technical specifications</p>

                    <p>Wattage: 1400 W Serviceability</p>

                    <p>Replacement</p>
                </div>
            </div>

            <div id="tab3" class="tab-content">
                <div class="title">Adidas men's running shoes 3</div>
                <div class="desc">
                    <p>
                        Easy care for your hair, SalonDry Compact , With 2 Years
                        Warranty............ Easy to use <br/>
                    </p>

                    <p>Easy storage hook Professional results</p>

                    <p>for careful drying</p>

                    <p>1400W for beautiful results</p>

                    <p>Narrow concentrator for focused airflow Technical specifications</p>

                    <p>Wattage: 1400 W Serviceability</p>

                    <p>Replacement</p>
                </div>
            </div>

        </div>

    </div>

    <!-- Start content area 2 -->

    <div class="content-area-2">
        <div class="right-col"></div>
    </div>

    <!-- End content area 2 -->

    <div class="clear"></div>

</div>

<!-- End Container -->

<div class="clear"></div>