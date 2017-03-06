var World = {
	loaded: false,

	init: function initFn() {
		this.createOverlays();
	},

	createOverlays: function createOverlaysFn() {
		// Initialize ClientTracker
		this.tracker = new AR.ClientTracker("assets/magazine.wtc", {
			onLoaded: this.worldLoaded
		});

		//this.imgButton = new AR.ImageResource("assets/wwwButton.jpg");

		/*
			Create a transparent video drawable:
			This bonus example shows you how to add transparent videos on top of a target. Transparent videos require some extra preparation work.

			Summarizing the required steps, here is what you need to do in order to use transparent videos in a simple list. We are describing each of the steps in more detail.

			1.	Produce a green screen (chroma key) video
			2.	Edit that video using standard video processing software
				and remove the green screen. Export your result into a file format,
				which can handle alpha channel information (e.g. Apple PreRes 4444)
			3.	Convert the video from step 2 using the script in the tools folder
			4.	Add it to a target image

			Producing a transparent video is usually done using a green screen for filming and a technique called chroma key to replace the green background with transparency. Extensive information is available on the internet that should help you get started on this topic.

			There are different video codecs that support alpha channels for motion picture and most of them will work as your raw material. We have extensively tested Apple ProRes 4444 codec for our own development and were satisfied with the results.

			The Wikitude SDK can render H.264 encoded videos, which is a codec that in practice does not support an alpha channel. The solution here is to include in the alpha channel in a separate (visible) part of the video. The video is split vertically consisting of a color and a alpha channel in the final video below each other.

			The upper half of the image transports the color information for the final video while the lower half includes a grayscale representation of the alpha layer. White areas will be fully opaque and black areas will be fully transparent. If you are familiar with Photoshop, think of the lower half as a mask. Resulting videos have an height that is exactly twice as big as the input video.

			To convert your raw video to a valid input video for the SDK we need to re-encode the video and automatically create the alpha mask. The script below uses ffmpeg to do so and wraps the necessary commands. Follow these simple steps:

			MacOS X
			Open the Terminal application
			Input cd <SDK>/tools/video/MacOSX. Replace <SDK> with the path to the SDK folder
			Execute sh convert.sh <input video> <output video>. Replace <input video> with the path to your transparent video and <output video> with the path where you want the output video to be stored.

			Windows
			Open the Command Line
			cd <SDK>\tools\video\Windows. Replace <SDK> with the path to the SDK folder
			Execute convert.bat <input video> <output video>. Replace <input video> with the path to your transparent video and <output video> with the path where you want the output video to be stored.
			This creates the required video with a vertically split color and alpha channel.

			Adding the transparent H.264 video to a target image is easy and accomplished in the same way as any other video is added. Just make sure to set the isTransparent property of the AR.VideoDrawable to true.
		*/
		/*var video = new AR.VideoDrawable("assets/transparentVideo.mp4", 0.7, {
		//var video = new AR.VideoDrawable("assets/video.mp4", 0.7, {
			offsetX: -0.2,
			offsetY: -0.12,
			isTransparent: true
		});

		// Create a button which opens a website in a browser window after a click
		this.imgButton = new AR.ImageResource("assets/wwwButton.jpg");
		var pageOneButton = this.createWwwButton("https://www.youtube.com/watch?v=TN-k5x7sDHs#action=share", 0.1, {
			offsetX: -0.05,
			offsetY: 0.2,
			zOrder: 1
		});

		video.play(-1);
        video.pause();*/

		//Agregado
		/*var video2 = new AR.VideoDrawable("assets/video.mp4", 0.7, {
        		//var video = new AR.VideoDrawable("assets/video.mp4", 0.7, {
        			offsetX: -0.2,
        			offsetY: -0.12,
        			isTransparent: true
        		});*/

        		// Create a button which opens a website in a browser window after a click
       /* this.imgButton = new AR.ImageResource("assets/wwwButton.jpg");
        		var pageOneButton2 = this.createWwwButton("https://www.google.co.ve/", 0.1, {
        			offsetX: -0.05,
        			offsetY: 0.2,
        			zOrder: 1
        		});*/

		/*video2.play(-1);
		video2.pause();*/

		//Fin de agregado

		/*
			Adding the video to the image target is straight forward and similar like adding any other drawable to an image target.

			Note that this time we use "*" as target name. That means that the AR.Trackable2DObject will respond to any target that is defined in the specified tracker. You can use wildcards to specify more complex name matchings. E.g. 'target_?' to reference 'target_1' through 'target_9' or 'target*' for any targets names that start with 'target'.
		*/

		var nombres = new Array("1f","2f", "3f", "1v", "1i");

		for (i=0; i < nombres.length; i++)
		{


			var pageOne = new AR.Trackable2DObject(this.tracker, nombres[i], {


            			video: null,

            			boton: null,

            			drawables:{

            				cam: null
            			},

            			onEnterFieldOfVision: function onEnterFiedOfVisionFn() {




								//Video Escuela Informatica
            					if (this.targetName == "1v")
								{

								if (this.video == null)
                                            				{
								  this.video = new AR.VideoDrawable("assets/video.mp4", 0.7, {
									offsetX: -0.2,
									offsetY: -0.12,
									isTransparent: false
								});

								this.drawables.addCamDrawable(this.video);

								this.video.play(-1);
								}
                                            				else
                                            				{
                                            					this.video.resume();
                                            				}

								}

								//Video transparente con la informacion del Monumento
								else if (this.targetName == "1i")
								{
									if (this.video == null)
                                                                                				{
									this.video = new AR.VideoDrawable("assets/transparentVideo.mp4", 0.7, {
										offsetX: -0.2,
										offsetY: -0.12,
										isTransparent: true
									});

									var imagen= new AR.ImageResource("assets/informacionlogo.jpeg");
									this.boton = new AR.ImageDrawable(imagen, 0.1 ,{

										offsetX: 0.1,
                                                			offsetY: 0.2,
                                                			zOrder: 1,

                                                			onClick : function ()
                                                			{
                                                			AR.context.openInBrowser("https://www.google.co.ve/");
                                                			}


									});


									this.drawables.addCamDrawable(this.video);

									this.video.play(-1);

								}
                                            				else
                                            				{
                                            					this.video.resume();
                                            				}

								//	this.drawables.cam = [drawableimagen];


								}

								//Foto-Informacion en la pared malek Shawarma
								else if (this.targetName == "1f")
								{
									var imagen= new AR.ImageResource("assets/1f.png");
									this.boton = new AR.ImageDrawable(imagen, 0.8 ,{

										offsetX: 0.1,
															offsetY: 0.2,
															zOrder: 1


									});
								}

								//Foto-Informacion Modulo 3
								else if (this.targetName == "2f")
								{
									var imagen= new AR.ImageResource("assets/2f.png");
									this.boton = new AR.ImageDrawable(imagen, 0.8 ,{

										offsetX: 0.1,
															offsetY: 0.2,
															zOrder: 1


									});

								}

								//Foto-informacion Laboratorio
								else if (this.targetName == "3f")
								{
									var imagen= new AR.ImageResource("assets/3f.png");
									this.boton = new AR.ImageDrawable(imagen, 0.8 ,{

										offsetX: 0.1,
															offsetY: 0.2,
															zOrder: 1


									});

								}


								if (this.boton != null)
								{
									this.drawables.addCamDrawable(this.boton);

								}


            			},
            			onExitFieldOfVision: function onExitFieldOfVisionFn() {
            				this.video.pause();
            			}
            		});
		}

		//agregado
		/*var pageOne = new AR.Trackable2DObject(this.tracker, "1*", {
        			drawables: {
        				cam: [video2, pageOneButton2]
        			},
        			onEnterFieldOfVision: function onEnterFieldOfVisionFn() {
        				video2.resume();
        			},
        			onExitFieldOfVision: function onExitFieldOfVisionFn() {
        				video2.pause();
        			}
        		});*/
	},

	/*createWwwButton: function createWwwButtonFn(url, size, options) {
		options.onClick = function() {
			// this call opens a url in a browser window
			AR.context.openInBrowser(url);
		};
		return new AR.ImageDrawable(this.imgButton, size, options);
	},*/

	worldLoaded: function worldLoadedFn() {
		var cssDivInstructions = " style='display: table-cell;vertical-align: middle; text-align: right; width: 50%; padding-right: 15px;'";
		var cssDivSurfer = " style='display: table-cell;vertical-align: middle; text-align: left; padding-right: 15px; width: 38px'";
		var cssDivBiker = " style='display: table-cell;vertical-align: middle; text-align: left; padding-right: 15px;'";
		document.getElementById('loadingMessage').innerHTML =
			"<div" + cssDivInstructions + ">Imagenes Referenciales:</div>" +
			"<div" + cssDivSurfer + "><img src='assets/surfer.png'></img></div>" +
			"<div" + cssDivBiker + "><img src='assets/bike.png'></img></div>";

		// Remove Scan target message after 10 sec.
		setTimeout(function() {
			var e = document.getElementById('loadingMessage');
			e.parentElement.removeChild(e);
		}, 20000);
	}
};

World.init();
