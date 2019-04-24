
//获取分类信息
var $courseTopClassbox = $('.course-top-classbox');
var $courseTopClassify = $('.course-top-classify');

$courseTopClassbox.find('.inner:first').addClass('class-show');
var classStr = '';
$courseTopClassbox.find('.inner').each(function(){
	var index = $(this).index();
	var title = $(this).find('.inner-title:first').text();
	var url = $(this).find('.inner-title:first').attr('data-url');
	
	if(index==0){
		classStr += '<li class="li-first"><a href="'+url+'">'+title+'</a></li>';
	}else{
		classStr += '<li><a href="'+url+'">'+title+'</a></li>';
	};

	$(this).find('.inner-class a:not(".inner-class a:first")').before('<em>|</em>')
});

classStr = '<ul>'+classStr+'</ul>';

$courseTopClassify.html(classStr);
$courseTopClassify.find('li').hover(function(){
	var index = $(this).index();
	$courseTopClassbox.show().find('.inner').eq(index).addClass('class-show').siblings('.inner').removeClass('class-show');
	$(this).addClass('active').siblings('li').removeClass('active');
},function(){
	$courseTopClassbox.hide();
	$courseTopClassify.find('li').removeClass('active');
});
$courseTopClassbox.hover(function(){
	var index = $(this).find('.class-show').index();
	$courseTopClassify.find('li').eq(index).addClass('active').siblings('li').removeClass('active');
	$(this).show();
},function(){
	$(this).hide();
	$courseTopClassify.find('li').removeClass('active');
});


//分类
var navFlag = 1;
$(".head-nav-allbtn,.course-top-classbox,.course-top-classify").mouseover(function(){
	$courseTopClassify.show();
}).mouseout(function(){
	if(navFlag!=0){
		$courseTopClassify.hide();
	};
});
function setNavFlag(i){
	$courseTopClassify.show();
	navFlag = i;
};


//评分计算
function coursePfCount(obj){
	$(obj).each(function(){
		var pfVal = $(this).attr('data-pf');
		var starWidth = $(this).find('.star').width();
		var mean = starWidth/5;
		var scoreWidth = mean*pfVal;
		$(this).find('.star-on').width(scoreWidth);
	});
};


//搜索
var searchDefaultUrl = ''; //默认搜索链接
function openSearchSelect(obj){
	if($(obj).attr('class').indexOf('search-selected-click')==-1){
		$(obj).addClass('search-selected-click');
		$(obj).next().show();
	}else{
		$(obj).removeClass('search-selected-click');
		$(obj).next().hide();
	};

	cancelBubble();
};

$(document).click(function(){
	$('.search-selected-click').next().hide();
	$('.search-selected-click').removeClass('search-selected-click');
});

function selectSearchType(obj,url){
	var text = $(obj).text();
	$('.search-selected').find('span').html(text);
	searchDefaultUrl = url; //改变url
};

function doSearch(url){
	var searchUrl = url;
	if(searchDefaultUrl!=''){
		searchUrl = searchDefaultUrl;
	};
	var key = $.trim($("#search-key-text").val());
	if (key == '' || key == '请输入关键字...') {
		$('#search-key-text').val('').focus();
	}else{
		if (searchUrl.indexOf("?")==-1){
			  searchUrl += "?key-"+key;

			}else{
			  searchUrl += "&key="+key;
		}
		location.href = searchUrl;
	};
};


//得到event事件
function getEvent(){
     if(window.event)    {return window.event;}
     func=getEvent.caller;
     while(func!=null){
         var arg0=func.arguments[0];
         if(arg0){
             if((arg0.constructor==Event || arg0.constructor ==MouseEvent
                || arg0.constructor==KeyboardEvent)
                ||(typeof(arg0)=="object" && arg0.preventDefault
                && arg0.stopPropagation)){
                 return arg0;
             }
         }
         func=func.caller;
     }
     return null;
};
//阻止冒泡
function cancelBubble()
{
    var e=getEvent();
    if(window.event){
        //e.returnValue=false;//阻止自身行为
        e.cancelBubble=true;//阻止冒泡
     }else if(e.preventDefault){
        //e.preventDefault();//阻止自身行为
        e.stopPropagation();//阻止冒泡
     }
};

//侧栏
var asideSpeed = 200;
$('.aside-operate li').hover(function(){
	$(this).find('span').fadeIn(asideSpeed);
	$(this).find('.code-box').fadeIn(asideSpeed);
},function(){
	$(this).find('span').fadeOut(asideSpeed);
	$(this).find('.code-box').stop().fadeOut(asideSpeed);
});
isAsideShow();
$(window).scroll(function(){
	isAsideShow();
});
function isAsideShow(){
	if($(window).scrollTop()>250){
		$('.aside-operate').fadeIn(asideSpeed);
	}else{
		$('.aside-operate').fadeOut(asideSpeed);
	}
};
function goTop(){
	$('html,body').animate({scrollTop:0},asideSpeed*3);
};