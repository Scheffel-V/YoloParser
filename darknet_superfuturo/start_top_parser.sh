YOLO_PARSER_JAR_PATH=$(cat YoloParserLocation.path | head -n 1)
sudo ./darknet detector demo YOLO_CIMA/exemplo.data YOLO_CIMA/exemplo.cfg YOLO_CIMA/exemplo_final.weights YOLO_CIMA/topo-1.mp4 | java -jar "$YOLO_PARSER_JAR_PATH" cli quantity