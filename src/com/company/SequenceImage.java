package com.company;

import javafx.scene.image.Image;

public class SequenceImage
{
    // assumes animation loops,
    //  each image displays for equal time
    public Image[] frames;
    public double duration;
    public double time;
    public boolean isAnimationFinished;

    public SequenceImage()
    {
        time = 0;
        isAnimationFinished = false;
    }

    public Image getFrame()
    {
        int index = (int)((time % (frames.length * duration)) / duration);

        if (index < frames.length)
        {
            time = time + 0.01;
            return frames[index];


        }
        else
        {
            time = 0;
            isAnimationFinished = true;
            return frames[0];
        }

    }
}
