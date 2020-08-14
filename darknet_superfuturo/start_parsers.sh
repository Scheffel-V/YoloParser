#!/bin/bash

sudo bash -c "echo starting parsers..."
./start_front_parser.sh & ./start_top_parser.sh && fg
