// @giphy: http://giphy.com/gifs/3ohze2apsm6Qpb281y
// @see: https://bl.ocks.org/npedrini/6030317
// @converter: https://blockchain.info/es/api/exchange_rates_api
var bitcoinsAmount = 0;
var exchangeRates = false;
var targetAmount = document.getElementById("amount");
//var targetExchange = document.getElementById("exchange");
/*---- AJAX ----*/
var xmlHttp = new XMLHttpRequest();

xmlHttp.onreadystatechange = function() {

    if (xmlHttp.readyState === 4) {
        if (xmlHttp.status >= 100 && xmlHttp.status < 300) {
            exchangeRates = JSON.parse(xmlHttp.responseText);
            //console.log(exchangeRates)
        } else {
            exchangeRates = false;
            console.error("ERROR! AJAX!", xmlHttp.status);
            console.info(JSON.parse(xmlHttp.responseText));
        }
    }
};

xmlHttp.open("GET", "https://blockchain.info/es/ticker?cors=true", true);
xmlHttp.send();
//https://blockchain.info/es/ticker

/*---- WEBSOCKETS ----*/
var blockchain = new WebSocket('wss://ws.blockchain.info/inv');

blockchain.onerror = function(error) {
    console.log('connection.onerror', error);
};

blockchain.onopen = function() {
    blockchain.send(JSON.stringify({
        "op": "unconfirmed_sub"
    })); //   subscribe to uncofirmed activity
    blockchain.send(JSON.stringify({
        "op": "blocks_sub"
    })); //   subscribe to new blocks
};

blockchain.onmessage = function(message) {
    var response = JSON.parse(message.data);
    if (response.op == "utx") {
        var amount = 0;
        for (var i = 0; i < response.x.out.length; i++) {
            amount += response.x.out[i].value;
        }
        //  amount is in satoshi
        //  1 BTC = 100,000,000 Satoshi (https://en.bitcoin.it/wiki/activity)
        response.amount = amount / 100000000;
        bitcoinsAmount += response.amount
        var contentAmount = "";

        if(exchangeRates) {
            var contentExchange = "";
            var sellExchange = "";
            for(var coin in exchangeRates){
                contentExchange += "<strong>"+coin+":</strong> "+ (bitcoinsAmount+exchangeRates[coin]["sell"]).toFixed(2)+ exchangeRates[coin]["symbol"]+" | "
                sellExchange += "<strong>"+coin+" (S):</strong> "+exchangeRates[coin]["sell"]+exchangeRates[coin]["symbol"] + " | "
                sellExchange += "<strong>"+coin+" (B):</strong> "+exchangeRates[coin]["buy"]+exchangeRates[coin]["symbol"] + " | "
            }
            document.getElementById("sell").innerHTML = sellExchange;
            document.getElementById("exchange").innerHTML = contentExchange;
        }
        contentAmount = "<p id='btc'><strong>BTC:</strong> " + bitcoinsAmount.toFixed(6) + "</p>";

        targetAmount.innerHTML = contentAmount;

    }
}



window.fetchData = function(rate, callback) {
    var oRefValues = {
        'EUR/USD' 		: 1.1143,
        'USD/JPY'		: 120.88,
        'USD/CAD'		: 1.2219,
        'BTC/USD' 		: 232.71,
        'CRUDE OIL WTI' : 58.59,
        'GOLD'			: 1208.21,
        'GBP/USD'		: 1.5520,
        'NZD/USD'		: 0.7368,
        'USD/CHF'		: 0.9364
    };
    var fRefValue = oRefValues[rate], fDiff = fRefValue/100;
    oRefValues[rate] = fRefValue + (-fDiff/2 + fDiff*Math.random());
    var json = {
        rate : rate,
        ts : 100 * Math.round(new Date().getTime() / 100),
        value : oRefValues[rate]
    };
    callback.call(window, JSON.stringify(json));
}

var cdatafb = {
    backgroundColor : '#ddd #eee',
    flat : true,
    graphset : [
        {
            backgroundColor : 'none',
            type : 'null',
            widgets : []
        }
    ]
};

var cdata = {
    backgroundColor : '#ddd #eee',
    flat : true,
    graphset : [
        {
            backgroundColor : 'none',
            type : 'null',
            widgets : [
                {
                    type : 'exchange',
                    rate : 'EUR/USD',
                    x : 10,
                    y : 10,
                    width : 450,
                    ticks : 100,
                    refresh : 500,
                    colors : [['#55154D', '#4E0E46'], '#fff', '#ccc']
                },
                {
                    type : 'exchange',
                    rate : 'USD/CAD',
                    x : 470,
                    y : 10,
                    colors : [['#56000E', '#5F0017'], '#fff', '#ccc']
                },
                {
                    type : 'exchange',
                    rate : 'USD/JPY',
                    decimals : 2,
                    x : 700,
                    y : 10,
                    width : 200,
                    height : 200,
                    colors : [['#014358', '#013D52'], '#fff', '#ccc']
                },

                {
                    type : 'exchange',
                    rate : 'BTC/USD',
                    decimals : 2,
                    x : 10,
                    y : 160,
                    refresh : 200,
                    ticks : 60,
                    colors : [['#264031', '#243C2E'], '#fff', '#ccc']
                },
                {
                    type : 'exchange',
                    rate : 'CRUDE OIL WTI',
                    decimals : 2,
                    x : 240,
                    y : 160,
                    colors : [['#264F14', '#244B13'], '#fff', '#ccc']
                },
                {
                    type : 'exchange',
                    rate : 'GOLD',
                    decimals : 2,
                    x : 470,
                    y : 160,
                    refresh : 2000,
                    height : 260,
                    colors : [['#704F00', '#6B4A00'], '#fff', '#ccc']
                },

                {
                    type : 'exchange',
                    rate : 'GBP/USD',
                    x : 10,
                    y : 310,
                    height : 110,
                    colors : [['#262400', '#242200'], '#fff', '#ccc']
                },
                {
                    type : 'exchange',
                    rate : 'NZD/USD',
                    x : 240,
                    y : 310,
                    height : 110,
                    colors : [['#260031', '#24002E'], '#fff', '#ccc']
                },
                {
                    type : 'exchange',
                    rate : 'USD/CHF',
                    x : 700,
                    y : 220,
                    width : 200,
                    height : 200,
                    colors : [['#363636', '#333333'], '#fff', '#ccc']
                }
            ]
        }
    ]
};

zingchart.render({
    id : 'zc',
    width : 910,
    height : 430,
    output : 'svg',
    strictmode : true,
    data : cdata
});

var SEQ = 0;
var md = function(p) {
    if (p.ev.button !== 0) {
        return;
    }
    /* locate widget */
    var wdata = null;
    for (var w=0;w<cdata.graphset[0].widgets.length;w++) {
        var rate = cdata.graphset[0].widgets[w].rate;
        var id = p.id + '-graph-' + p.id + rate.replace(/[^a-zA-Z0-9]/g, '');
        if (id === p.graphid) {
            wdata = cdata.graphset[0].widgets[w];
            wdata.x = 0;
            wdata.y = 0;
            wdata.width = 220;
            wdata.height = 100;
        }
    }
    if (wdata) {
        cdatafb.graphset[0].widgets = [wdata];
        p.ev.preventDefault();
        $(document.body).append('<div id="fb" style="position:absolute;box-shadow:3px 3px 3px #333;z-index:100;top:0px;left:0px;width:' + wdata.width + 'px;height:' + wdata.height + 'px;"></div>');
        $('#fb').css('left', (p.ev.clientX - wdata.width/2) + 'px').css('top', (p.ev.clientY - wdata.height/2) + 'px');
        var mm = function(ev) {
            ev.preventDefault();
            $('#fb').css('left', (ev.clientX - wdata.width/2) + 'px').css('top', (ev.clientY - wdata.height/2 + $(window).scrollTop()) + 'px');
        };
        var mu = function(ev) {
            $('#db').append('<div id="fb' + SEQ + '" style="float:left;"></div>');
            zingchart.render({
                id : 'fb' + SEQ,
                width : wdata.width,
                height : wdata.height,
                output : 'svg',
                strictmode : true,
                data : cdatafb
            });
            SEQ++;
            $('#fb').remove();
            zingchart.exec('fb', 'destroy');
            $(document).unbind('mouseup', mu);
            $(document).unbind('mousemove', mm);
        };
        $(document).bind('mousemove', mm);
        $(document).bind('mouseup', mu);
        zingchart.render({
            id : 'fb',
            width : wdata.width,
            height : wdata.height,
            output : 'svg',
            strictmode : true,
            data : cdatafb
        });
    }
};
zingchart.bind('zc', 'mousedown', md);
