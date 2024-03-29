
// ------------------------------------------
// Rellax.js - v0.1.0
// Buttery smooth parallax library
// Copyright (c) 2016 Moe Amaya (@moeamaya)
// MIT license
//
// Thanks to Paraxify.js and Jaime Cabllero
// for parallax concepts 
// ------------------------------------------

(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define([], factory);
    } else if (typeof module === 'object' && module.exports) {
        // Node. Does not work with strict CommonJS, but
        // only CommonJS-like environments that support module.exports,
        // like Node.
        module.exports = factory();
    } else {
        // Browser globals (root is window)
        root.Rellax = factory();
  }
}(this, function () {
  var Rellax = function(el, options){ 
    "use strict";
	var first=0;
    var self = Object.create(Rellax.prototype);

    // Rellax stays lightweight by limiting usage to desktops/laptops
//  if (typeof window.orientation !== 'undefined') { return; }

    var posY = 0; // set it to -1 so the animate function gets called at least once
    var screenY = 0;
    var blocks = [];
    
    // check what requestAnimationFrame to use, and if
    // it's not supported, use the onscroll event
    var loop = window.requestAnimationFrame ||
    	window.webkitRequestAnimationFrame ||
    	window.mozRequestAnimationFrame ||
    	window.msRequestAnimationFrame ||
    	window.oRequestAnimationFrame ||
    	function(callback){ setTimeout(callback, 1000 / 60); };

    // Default Settings
    self.options = {
      speed: -2
    };

    // User defined options (might have more in the future)
    if (options){
      Object.keys(options).forEach(function(key){
        self.options[key] = options[key];
      });
    }

    // If some clown tries to crank speed, limit them to +-10
    if (self.options.speed < -10) {
      self.options.speed = -10;
    } else if (self.options.speed > 10) {
      self.options.speed = 10;
    }

    // By default, rellax class
    if (!el) {
      el = '.rellax';
    }

    // Classes
    if (document.getElementsByClassName(el.replace('.',''))){
      self.elems = document.getElementsByClassName(el.replace('.',''));
    }

    // Now query selector
    else if (document.querySelector(el) !== false) {
      self.elems = querySelector(el);
    }

    // The elements don't exist
    else {
      throw new Error("The elements you're trying to select don't exist.");
    }


    // Let's kick this script off
    // Build array for cached element values
    // Bind scroll and resize to animate method
    var init = function() {
      screenY = window.innerHeight;
      setPosition();

      // Get and cache initial position of all elements
      for (var i = 0; i < self.elems.length; i++){
        var block = createBlock(self.elems[i]);
        blocks.push(block);
      }
			
			window.addEventListener('resize', function(){
			  animate();
			});
			
			// Start the loop
      update();
      
      // The loop does nothing if the scrollPosition did not change
      // so call animate to make sure every element has their transforms
      animate();
    };


    // We want to cache the parallax blocks'
    // values: base, top, height, speed
    // el: is dom object, return: el cache values
    var createBlock = function(el) {

      // initializing at scrollY = 0 (top of browser)
      // ensures elements are positioned based on HTML layout
      var posY = 0;

      var blockTop = posY + el.getBoundingClientRect().top;
      var blockHeight = el.clientHeight || el.offsetHeight || el.scrollHeight;

      // apparently parallax equation everyone uses
      var percentage = (posY - blockTop + screenY) / (blockHeight + screenY);

      // Optional individual block speed as data attr, otherwise global speed
      var speed = el.getAttribute('data-rellax-speed') ? el.getAttribute('data-rellax-speed') : self.options.speed;
      var base = updatePosition(percentage, speed);

      // Store non-translate3d transforms
      var cssTransform = el.style.cssText.slice(11);

      return {
        base: base,
        top: blockTop,
        height: blockHeight,
        speed: speed,
        style: cssTransform
      };
    };


    // set scroll position (posY)
    // side effect method is not ideal, but okay for now
    // returns true if the scroll changed, false if nothing happened
    var setPosition = function() {
    	var oldY = posY;
    	
      if (window.pageYOffset !== undefined) {
      
        posY = window.pageYOffset;
      } else {
        posY = (document.documentElement || document.body.parentNode || document.body).scrollTop;
        
      }
      
      if (oldY != posY) {
//    	console.log("oldY"+oldY)
//    	console.log("posY"+posY)
      	// scroll changed, return true
      	return true;
      }
      
      // scroll did not change
      return false;
    };


    // Ahh a pure function, gets new transform value
    // based on scrollPostion and speed
    var updatePosition = function(percentage, speed) {
      var value = (speed * (100 * (1 - percentage)));
      return Math.round(value);
    };


    //
		var update = function() {
			if (setPosition()) {
				animate();
	    }
	    
	    // loop again
	    loop(update);
		};
		
    // Transform3d on parallax element
    var animate = function() {
    	for (var i = 0; i < self.elems.length; i++){
        var percentage = ((posY - blocks[i].top + screenY) / (blocks[i].height + screenY));
//		console.log("posY:"+posY+"blocks[i].top:"+blocks[i].top+"screenY:"+screenY+"blocks[i].height:"+blocks[i].height+"screenY:"+screenY+"blocks[i].speed:"+blocks[i].speed+"blocks[i].base:"+blocks[i].base)
//		console.log(updatePosition(percentage, blocks[i].speed))
        // Subtracting initialize value, so element stays in same spot as HTML
        var position = updatePosition(percentage, blocks[i].speed) - blocks[i].base;
//		console.log("position"+position)
//		console.log("positionaa"+updatePosition(percentage, blocks[i].speed))
//		console.log("position"+blocks[i].base)
        // Move that element
       // console.log("@@@:"+$("#"+self.elems[i].id).parents(".par").offset().top)
        first = (updatePosition((($("#"+self.elems[i].id).parent().parent().offset().top - blocks[i].top + screenY) / (blocks[i].height + screenY)), blocks[i].speed) - blocks[i].base)
          var translate = 'translate3d(0,' + (position-first) + 'px' + ',0) rotate('+position/5+'deg)' + blocks[i].style ;
          //console.log("@@@@@@@@123123@@"+i)
          self.elems[i].style.cssText = ''+'-webkit-transform:'+translate+';-moz-transform:'+translate+';transform:'+translate+';'
      }
    };


//for (var i = 0; i < self.elems.length; i++){
//      var percentage = ((posY - blocks[i].top + screenY) / (blocks[i].height + screenY));
//		console.log("posY:"+posY+"blocks[i].top:"+blocks[i].top+"screenY:"+screenY+"blocks[i].height:"+blocks[i].height+"screenY:"+screenY+"blocks[i].speed:"+blocks[i].speed+"blocks[i].base:"+blocks[i].base)
////		console.log(updatePosition(percentage, blocks[i].speed))
//      // Subtracting initialize value, so element stays in same spot as HTML
//      var position = updatePosition(percentage, blocks[i].speed) - blocks[i].base;
//
//      console.log("@@@:"+$("#"+self.elems[i].id).parents(".par").offset().top)
//      first = (updatePosition((($("#"+self.elems[i].id).parents(".par").offset().top - blocks[i].top + screenY) / (blocks[i].height + screenY)), blocks[i].speed) - blocks[i].base)
//      
//      
//        var translate = 'translate3d(0,' + (position-first) + 'px' + ',0) rotate('+position/5+'deg)' + blocks[i].style ;
//        var translateOne='translate3d(0,' + (position-first) + 'px' + ',0) rotate('+position/4+'deg)' + blocks[i].style ;
////        var translateTwp='translate3d(0,' + (position-first) + 'px' + ',0) rotate('+position/5+'deg)' + blocks[i].style ;
////        var translateThree='translate3d(0,' + (position-first) + 'px' + ',0) rotate('+position/5+'deg)' + blocks[i].style ;
//          
//       
//       console.log($("#"+self.elems[i].id))
//       if(self.elems[i].id=="fruit2"){
//       	 self.elems[i].style.cssText = ''+'-webkit-transform:'+translate+';-moz-transform:'+translate+';transform:'+translate+';'
//       	 console.log("===========fruit2==========")
//       	  console.log(self.elems[i].id)
//       }else{
//       	 self.elems[i].style.cssText = ''+'-webkit-transform:'+translateOne+';-moz-transform:'+translateOne+';transform:'+translateOne+';'
//       }



    init();
    Object.freeze();
    return self;
  };
  return Rellax;
}));
