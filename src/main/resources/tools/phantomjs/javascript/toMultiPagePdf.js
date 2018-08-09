var page = require('webpage').create(),
    system = require('system'),
    address, output, size;

if (system.args.length < 3 || system.args.length > 5) {
    console.log('Usage: toMultiPagePdf.js URL filename [paperwidth*paperheight|paperformat] [zoom]');
    console.log('  paper (pdf output) examples: "5in*7.5in", "10cm*20cm", "A4", "Letter"');
    phantom.exit(1);
} else {
    address = system.args[1];
    output = system.args[2];
    /*size of browser*/
    page.viewportSize = { width: 600, height: 600 };
    /*
    if (system.args.length > 3 && system.args[2].substr(-4) === ".pdf") {
        size = system.args[3].split(��*��);
        page.paperSize = size.length === 2 ? { width: size[0], height: size[1], margin: ��0px�� }
                                           : { format: ��A4��, orientation: ��portrait��, margin: ��1cm�� };
    }
    */
    /* ie and chrome view diffrent format of pdf */
    page.settings.userAgent = 'Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36';
    page.paperSize = { format: 'A4', orientation: 'portrait', margin: '0.8cm' };
    page.zoomFactor = 1;
    page.settings.loadImages = true;
    //some question about the page language
    page.open(address, function (status) {
        if (status !== 'success') {
            console.log('Unable to load the address!');
        } else {
            //page.render(output);
            //phantom.exit();
            
            window.setTimeout(function () {
                page.render(output);
                phantom.exit();
            }, 200); //setting the time is enough to loading the page. document.ready
            
        }
    });
}