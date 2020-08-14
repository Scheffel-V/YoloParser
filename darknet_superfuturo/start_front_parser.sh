YOLO_PARSER_JAR_PATH=$(cat YoloParserLocation.path | head -n 1)
sudo ./darknet detector demo YOLO_FRENTE/exemplo.data YOLO_FRENTE/exemplo.cfg YOLO_FRENTE/exemplo_final.weights YOLO_FRENTE/frente-1.mp4 | java -jar "$YOLO_PARSER_JAR_PATH" cli unity
