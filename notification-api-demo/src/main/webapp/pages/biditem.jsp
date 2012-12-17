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

    <title>Bid and Win</title>

</head>

<!-- Start Container -->
<div id="container">

    <!-- Start content area 1 -->
    <div class="content-area-1">


        <div class="item-data-box">
            <div class="item-tit">${listing.item.name}</div>
            <div class="clear"></div>
            <div class="item-data-col1">
                <div class="img-box">
                    <img src="${listing.item.imagePath}" width="222" height="166"/>
                </div>
            </div>
            <div class="item-data-col2">
                <div class="count-dwn-wrapper">
                    <div class="hr">${hours}</div>
                    <div class="min">${minutes}</div>
                    <div class="sec">${seconds}</div>
                    <div class="bids">${listing.bidCount}</div>
                </div>
                <div class="clear"></div>
                <div class="price-box">
                    <span class="itm-price">Max Bid : LKR.${listing.maxBid}</span>
                </div>

                <div class="clear"></div>

                <form action="place-bid.html" method="post">
                    <input type="hidden" value="${listing.id}" name="lt"/>
                    <div class="price-box2">
                        <span class="itm-price">Quantity: ${listing.quantityRemain} </span>
                        <span class="last-bid">Item condition: New</span>
                        <span class="last-bid">Bid: <input name="bidAmount" type="text" id="textfield" size="7"/></span>
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
                <div class="title">Description</div>
                <div class="desc">
                    <p>
                        ${listing.item.description}
                    </p>
                    <p>
                        Item Location : ${listing.itemLocation}
                    </p>
                </div>
            </div>

            <div id="tab2" class="tab-content">
                <div class="title">Shipping Details</div>
                <div class="desc">
                    <p>
                        Shipping Cost : LKR. ${listing.shippingCost}
                    </p>
                    <p>
                        Shipping Instructions : ${listing.shippingInstructions}
                    </p>
                    <p>
                        Return Instructions : ${listing.returnInstructions}
                    </p>

                </div>
            </div>

            <div id="tab3" class="tab-content">
                <div class="title">Similar Items</div>
                <div class="desc">

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