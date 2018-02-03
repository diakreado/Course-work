package com.maltsev.labyrinth.presenter.tempdata;

import org.junit.Test;

import static org.junit.Assert.*;


public class SizeOfTextureTest {

    @Test
    public void verifySizeOfTexture() throws Exception {

        int width = 10;
        int height = 10;

        SizeOfTexture sizeOfTexture = new SizeOfTexture(width,height);

        assertEquals(sizeOfTexture.getWidth(),width);
        assertEquals(sizeOfTexture.getHeight(),height);

        assertEquals(sizeOfTexture.toString(), "width= " + width + ";  height= " + height + ";");
    }
}