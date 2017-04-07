require.config({
    paths: {
        'jQuery': 'vendor/jquery-1.9.0.min',
        'underscore': 'vendor/underscore-1.9.min'
    },
    shim: {
        'jQuery': {
            exports: '$'
        },
        'underscore': {
            exports: '_'
        }
    }
});
require(['module1', 'path/to/module2', 'jQuery'], function (module1ref, module2ref, $) {
 
    // do something with the loaded modules
    var module1 = new module1ref(),
          module2 = new module2ref();
    console.log(module1.getName() === module2.getModule1Name()); // true
    console.log('jQuery version:', $.fn.jquery); // 1.9.0
 
});
