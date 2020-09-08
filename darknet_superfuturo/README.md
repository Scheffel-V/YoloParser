![Darknet Logo](http://pjreddie.com/media/files/darknet-black-small.png)


# might be helpful for the future:
https://github.com/AlexeyAB/darknet#how-to-improve-object-detection

# Darknet #
Darknet is an open source neural network framework written in C and CUDA. It is fast, easy to install, and supports CPU and GPU computation.

Yolo v4 paper: https://arxiv.org/abs/2004.10934

Yolo v4 source code: https://github.com/AlexeyAB/darknet

Yolov v4 tiny discussion: https://www.reddit.com/r/MachineLearning/comments/hu7lyt/p_yolov4tiny_speed_1770_fps_tensorrtbatch4/

Useful links: https://medium.com/@alexeyab84/yolov4-the-most-accurate-real-time-neural-network-on-ms-coco-dataset-73adfd3602fe?source=friends_link&sk=6039748846bbcf1d960c3061542591d7

For more information see the [Darknet project website](http://pjreddie.com/darknet).

For questions or issues please use the [Google Group](https://groups.google.com/forum/#!forum/darknet).

## How to generate output video
	- ensure `MakeVideo/in` is empty
	- run `darknet` command (if unsure checkout darknet docs or start parser scripts), but use `-prefix MakeVideo/in/pic_`
	- go to `MakeVideo/in` and run `./cvJoin.py` or `python3 cvJoin.py`
	- you should have an output video 

## How to start parsers
	- `YoloParserLocation.path`: must be configured with path to YoloParser.jar (follow instructions inside file).
	- `start_top_parser.sh`: starts darknet detector with top parser config (weights, classes, ...). Starts YoloParser instance redirects darknet output to it.
	- `start_front_parser.sh`: starts darknet detector with front parser config (weights, classes, ...). Starts YoloParser instance redirects darknet output to it.
	- `start_parsers.sh`: calls both `start_top_parser.sh` and `start_front_parser.sh`.
