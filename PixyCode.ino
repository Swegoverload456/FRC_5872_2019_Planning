//
// begin license header
//
// This file is part of Pixy CMUcam5 or "Pixy" for short
//
// All Pixy source code is provided under the terms of the
// GNU General Public License v2 (http://www.gnu.org/licenses/gpl-2.0.html).
// Those wishing to use Pixy source code, software and/or
// technologies under different licensing terms should contact us at
// cmucam@cs.cmu.edu. Such licensing terms are available for
// all portions of the Pixy codebase presented here.
//
// end license header
//
// This sketch is a good place to start if you're just getting started with 
// Pixy and Arduino.  This program simply prints the detected object blocks 
// (including color codes) through the serial console.  It uses the Arduino's 
// ICSP port.  For more information go here:
//
// http://cmucam.org/projects/cmucam5/wiki/Hooking_up_Pixy_to_a_Microcontroller_(like_an_Arduino)
//
// It prints the detected blocks once per second because printing all of the 
// blocks for all 50 frames per second would overwhelm the Arduino's serial port.
//
   
#include <SPI.h>  
#include <Pixy.h>

// This is the main Pixy object 
Pixy pixy;

void setup()
{
  Serial.begin(9600);
  Serial.print("Starting...\n");
  
  //X
  
  
  //pinMode(3, OUTPUT);

  //Y
  pinMode(7, OUTPUT);

  pixy.init();
}

void loop()
{ 
  static int i = 0;
  int j;
  uint16_t blocks;
  char buf[32]; 
  
  // grab blocks!
  blocks = pixy.getBlocks();
  
  // If there are detect blocks, print them!
  if (blocks)
  {
    i++;
    
    // do this (print) every 50 frames because printing every
    // frame would bog down the Arduino
      //sprintf(buf, "Detected %d:\n", blocks);
      //Serial.print(buf);
      for (j=0; j<blocks; j++)
      {

        if(pixy.blocks[0].x < 10){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          
          
        }
        else if(pixy.blocks[0].x >= 10 && pixy.blocks[0].x < 20){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 20 && pixy.blocks[0].x < 30){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 30 && pixy.blocks[0].x < 40){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 40 && pixy.blocks[0].x < 50){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 50 && pixy.blocks[0].x < 60){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 60 && pixy.blocks[0].x < 70){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 70 && pixy.blocks[0].x < 80){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 80 && pixy.blocks[0].x < 90){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 90 && pixy.blocks[0].x < 100){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 100 && pixy.blocks[0].x < 110){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 110 && pixy.blocks[0].x < 120){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 120 && pixy.blocks[0].x < 130){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 130 && pixy.blocks[0].x < 140){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 140 && pixy.blocks[0].x < 150){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 150 && pixy.blocks[0].x < 160){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 160 && pixy.blocks[0].x < 170){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 170 && pixy.blocks[0].x < 180){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
        
        }
        else if(pixy.blocks[0].x >= 180 && pixy.blocks[0].x < 190){
          
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          
        
        }
        else if(pixy.blocks[0].x >= 190 && pixy.blocks[0].x < 200){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
        
        }
        else if(pixy.blocks[0].x >= 200 && pixy.blocks[0].x < 210){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);

        }
        else if(pixy.blocks[0].x >= 210 && pixy.blocks[0].x < 220){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);

        }
        else if(pixy.blocks[0].x >= 220 && pixy.blocks[0].x < 230){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);

        }
        else if(pixy.blocks[0].x >= 230 && pixy.blocks[0].x < 240){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);

        }
        else if(pixy.blocks[0].x >= 240 && pixy.blocks[0].x < 250){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);

        }
        else if(pixy.blocks[0].x >= 250 && pixy.blocks[0].x < 260){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);

        }
        else if(pixy.blocks[0].x >= 260 && pixy.blocks[0].x < 270){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);

        }
        else if(pixy.blocks[0].x >= 270 && pixy.blocks[0].x < 280){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);

        }
        else if(pixy.blocks[0].x >= 280 && pixy.blocks[0].x < 290){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);

        }
        else if(pixy.blocks[0].x >= 290 && pixy.blocks[0].x < 300){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);

        }
        else if(pixy.blocks[0].x >= 300 && pixy.blocks[0].x < 310){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);

        }
        else if(pixy.blocks[0].x >= 310 && pixy.blocks[0].x < 320){
        
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);

        }
        else if(pixy.blocks[0].x < 310){

          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, HIGH);
          delay(15);
          digitalWrite(7, LOW);
          delay(15);

        }


                
        
        
        //sprintf(buf, "  block %d: ", j);
        //Serial.print(buf); 
        //pixy.blocks[j].print();
        Serial.print("X: ");
        Serial.print(pixy.blocks[0].x);   
        Serial.print("\n");
        /*Serial.print("Y: ");
        Serial.print(pixy.blocks[0].y);
        analogWrite(5, pixy.blocks[0].y);
        Serial.print("\n");*/

         

        
      
    }
  }  
}
