<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Add Your favicon here -->
    <!--<link rel="icon" href="img/favicon.ico">-->

    <title>protaz.tk</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Animation CSS -->
    <link href="css/animate.min.css" rel="stylesheet">

    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body id="page-top">
<div class="navbar-wrapper">
    <nav class="navbar navbar-default navbar-fixed-top navbar-scroll" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="page-scroll" href="#features">Features</a></li>
                    <li><a class="page-scroll" href="#counter">Counter</a></li>
                    <li><a class="page-scroll" href="#contact">Contact</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<section style="margin-top: 66px;">
    <div class="container header-back two">
        <div class="row" style="padding-top: 25px;">
            <div class="col-sm-4 col-sm-offset-4">
                <img src="/img/logo_white.png" style="height: 200px;"/>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-3" id="create">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3" id="urls">

            </div>
        </div>
    </div>
</section>


<section id="features" class="features">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>More and more extra great feautres</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 features-text">
                <h2>A lot of links</h2>
                <i class="fa fa-bar-chart big-icon pull-right"></i>
                <p>Make those huge urls into small ones with our awesome shortening algorithm, we support up to
                    2,147,483,647</p>
            </div>
            <div class="col-lg-5 features-text">
                <h2>Blazing fast </h2>
                <i class="fa fa-bolt big-icon pull-right"></i>
                <p>With our Spring boot / Java backend we can warranty fast response times</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 features-text">
                <h2>Modern UI</h2>
                <i class="fa fa-heart big-icon pull-right"></i>
                <p>Base on Ractive.js "The diamond age of web development"</p>
            </div>
            <div class="col-lg-5 features-text">
                <h2>Share </h2>
                <i class="fa fa-users big-icon pull-right"></i>
                <p>Share the link and gatter all the information of the visitors</p>
            </div>
        </div>
    </div>

</section>


<section id="counter" class="gray-section">

    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-sm-offset-3 text-center" id="counterDiv">
            </div>
        </div>
    </div>

</section>

<section id="contact" class="gray-section " style="margin-top: 0px;">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <a href="https://github.com/blzb/protaz.tk" class="btn btn-primary">Fork Me</a>
                <p class="m-t-sm">
                    Or follow us on social platform
                </p>
                <ul class="list-inline social-icon">
                    <li><a href="https://twitter.com/blzb"><i class="fa fa-twitter"></i></a>
                    </li>
                    <li><a href="https://github.com/blzb"><i class="fa fa-github"></i></a>
                    </li>
                    <li><a href="https://www.linkedin.com/in/angelpimentel/"><i class="fa fa-linkedin"></i></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center m-t-lg m-b-lg">
                <p><strong>&copy; 2015 @blzb</strong><br/></p>
            </div>
        </div>
    </div>
</section>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ractive/0.7.3/ractive.min.js"></script>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/pace.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/classie.js"></script>

<script id="main" type="text/ractive">
    {{#items.length}}
				<div class="wrapper wrapper-content">
					<!-- Here, we compare the total number of tasks (`items.length`) with the number of completed tasks (`completedTasks().length`). This calculation happens reactively, so we never need to manually trigger an update. When the `change` event fires on the input, the `toggleAll` event fires on the Ractive instance. -->
						{{#items:i}}
							<!-- The {{>item}} partial is defined in the script tag below -->
							{{>item}}
						{{/items}}
				</div>
	{{/items.length}}



</script>


<script id="item" type="text/ractive">
<div class="vote-item  animated fadeInRight">
    <div class="row">

        <div class="col-md-10">
            <div class="vote-actions">

                <div style="padding-top: 10px;">{{visits}}
                </div>
                <a href="#">
                    <i class="fa fa-area-chart" style="font-size: 30px;"> </i>
                </a>
            </div>
            <a href="{{shortUrl}}" target="_blank" class="vote-title">
                {{shortUrl}}
            </a>
            <a href="{{originalUrl}}" target="_blank" class="vote-subtitle">
                {{originalUrl}}
            </a>
            <div class="vote-info">
                <i class="fa fa-clock-o"></i> <a href="#">{{createdAt}}</a>
            </div>
        </div>
        <div class="col-md-2">
            <button type="button" class="btn btn-outline btn-primary" on-click='copyToClipboard'>Copy</button>
        </div>
    </div>
</div>




</script>
<script id="form" type="text/ractive">

<h1 class="page-title text-center">LET'S SHORTEN THAT LINK</h1>
<br/>
{{#if invalidUrl}}
<div class="alert alert-warning alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Warning!</strong> That url is not valid
</div>
{{/if}}
<div class="input-group"><input placeholder="Type your url..." autofocus type="text" class="form-control input-lg"> <span
        class="input-group-btn"> <button on-click='newUrl' type="button" class="btn btn-primary btn-lg"
                                         style="height: 46px; padding-top: 6px; padding-bottom: 6px; padding-left: 30px; padding-right: 30px; font-size: 20px">Go!
                                        </button>
                                        </span>
</div>


</script>
<script id="counters" type="text/ractive">
                <h3>
                    Links shortened
                </h3>
<h1>{{counters.totalLinks}}</h1>

{{#if (page.content.length>0)}}
				<div class="wrapper wrapper-content">
					<!-- Here, we compare the total number of tasks (`items.length`) with the number of completed tasks (`completedTasks().length`). This calculation happens reactively, so we never need to manually trigger an update. When the `change` event fires on the input, the `toggleAll` event fires on the Ractive instance. -->
						{{#page.content:i}}
							<!-- The {{>item}} partial is defined in the script tag below -->
							{{>item}}
						{{/page.content}}
				</div>
				<div class="btn-group">
				    <button class="btn btn-primary" on-click='prevPage'>Prev</button><button class="btn btn-primary" on-click='nextPage'>Next</button>
                </div>

{{else}}
    <button class="btn btn-primary" on-click='loadUrls'>Show urls</button>
{{/if}}

</script>
<script src="js/app.js"></script>
</body>
</html>
