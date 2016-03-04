# How to Display Bitmap Fonts in JME #
JMonkeyEngine now allows you to display any font you wish in your game. How? Well it requires a few preliminary steps to prepare the font file and texture file, and then it's pretty plain sailing. Follow this tutorial and you'll see! Please note this tutorial is pretty much exclusively for Windows users. If you have a Mac (or dare I say using Linux?) you might want to find yourself a Windows PC to get steps 1-3 completed.

---

## Step 1 - Choose your font. ##
You could just pick Arial, but why not take a trip to http://www.1001freefonts.com and find a font you fancy playing around with. For this tutorial, I am going to use "Battlefield" (http://www.1001freefonts.com/Battlefield.php). Click the "Windows Download" icon and save the zip file to your PC.

---

## Step 2 - Install the font. ##
In a few moments time, we're going to be using a free app called "Bitmap Font Generator". In order to output our font and texture file, the font must be "installed" on your machine. Follow these steps:

  * Unzip the Battlefield.zip file to a new folder.
  * Go to "Control Panel" and double-click "Fonts".
  * Select File -> Install New Font
  * In the box at the bottom, navigate to the folder that contains the unzipped Battlefield font.
  * The fonts should now appear in the "List of fonts" dialog. Select just the "Battlefield (TrueType)" font for now.
  * Click "OK".

Well done. Font installed!

**IMPORTANT:** Please bear in mind that these fonts are free for non-commercial use. If you want to consider a commercial license, you can download 10,000 fonts for $20 here: http://www.ultimatefontdownload.com/

---

## Step 3 - Creating the font and texture file. ##
JME needs a font file and a texture file. The texture file is an image with all the characters of the font. The font file itself contains the mapping of where each charater is in the image.

The tool we're going to use to do this is the (creatively named) Bitmap font generator (http://www.angelcode.com/products/bmfont). Download and install the latest version. Start it up and follow these steps:

  * Firstly, go in to the "Font Settings" (F). Choose the following settings:
    * Face: Battlefield
    * Charset: Unicode
    * Size: 16
    * Height: 100%
    * Font smoothing: Checked
    * All other checkboxes: Unchecked

<img src='http://farm5.static.flickr.com/4006/4679596699_325251e86f.jpg' alt='bm-image1' width='290' height='409' />

  * Click "OK"
  * Select "Export Options" (T). Settings:
    * Width/Height: Both 512 (This is the size of the image being created)
    * Bit depth: 32 (Haven't tried 8, so going with what I know!)
    * Presets: White text with alpha (this will update the values above).
    * Font descriptor: Text (This is the format that JME uses)
    * Textures: png (You can use TGA if you wish, but the file is much larger, and I couldn't see any real performance benefit)

<img src='http://farm5.static.flickr.com/4024/4679596649_496806865d_b.jpg' alt='bm-image2' width='288' height='567' />

  * Click "OK"
  * Select the top 4 font sets (on the right hand side). These will be the characters that will be put on in to the texture image. If you're thinking of creating international characters, you might want to investigate fonts that can cater for this. If you select a lot of characters, you'll probably need to increase the width/height of the output image (which you currently have as 512x512).

<img src='http://farm5.static.flickr.com/4012/4681069669_76affb91ed_b.jpg' alt='bm-image3' width='762' height='512' />

  * Now create the font file and texture. Select "Options -> Save bitmap font as". Save the file as "Battlefield16.fnt" in a folder that you can easily remember. I reccomend creating a new one so you can clearly see what's going on.

Having saved the file, go and have a look at the "battlefield16.fnt" file in Notepad/Textpad. Also take a look at the generated "battlefield16\_0.png" file. Hopefully you can get an idea of what is going on here. JME can use the ".fnt" file to work out where to map each character of the ".png" file on to the screen. You'll probably need to zoom in on the ".png" file as white with an alpha background is going to be hard to see!

Great. Finally we can dive in to some JME to get this all working.

---

## Step 4 - Putting it together in JME. ##
OK, I'm going to just dump the code below, and talk you through it further down. Have a quick skim through and see what you think.
```
import com.jme.app.SimpleGame;
import com.jme.math.Vector3f;
import com.jmex.angelfont.Rectangle;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.Spatial.CullHint;
import com.jme.scene.Spatial.LightCombineMode;
import com.jme.scene.shape.Box;
import com.jmex.angelfont.BitmapFont;
import com.jmex.angelfont.BitmapFontLoader;
import com.jmex.angelfont.BitmapText;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitmapFontExample extends SimpleGame {

    public static void main(String[] args) {
        SimpleGame sg = new BitmapFontExample();
        sg.setConfigShowMode(ConfigShowMode.AlwaysShow);
        sg.start();
    }

    protected void simpleInitGame() {
        addCube();
        testBitmapFont();
    }

    private void addCube() {
        Box b = new Box("Cube", new Vector3f(0f, 0f, -100f), 20f, 20f, 20f);
        rootNode.attachChild(b);
        rootNode.updateRenderState();
    }

    private void testBitmapFont() {
        File fontFile = new File("c:\\testfont\\battlefield14.fnt");
        File textureFile = new File("c:\\testfont\\battlefield14_0.png");

        BitmapFont fnt = null;

        try {
            fnt = BitmapFontLoader.load(fontFile.toURI().toURL(), textureFile.toURI().toURL());
        } catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Unable to load font: " + ex);
            System.exit(1);
        }

        BitmapText txt = new BitmapText(fnt, false);
        txt.setBox(new Rectangle(0, 0, 320, 100));
        txt.setSize(16);

        // Setting the text to green, and alpha so it blends a bit with the background
        txt.setDefaultColor(new ColorRGBA(0f, 1f, 0f, .8f));
        txt.setLightCombineMode(LightCombineMode.Off);

        txt.setText("Richard Hawkes is really cool, really very cool in fact!");
        txt.update();

        Node orthoNode = new Node("Ortho Node for Fonts");
        orthoNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
        orthoNode.setLocalTranslation(10, 100, 0);
        orthoNode.setCullHint(CullHint.Never);

        orthoNode.attachChild(txt);
        rootNode.attachChild(orthoNode);
        rootNode.updateRenderState();
    }
}
```
A lot there. It's the `testBitmapFont()` that I will talk you through.

The `fontFile` and `textureFile` will be the files you saved earlier. Be sure to update the directory to the directory where you installed your font/texture.

Next we create a `BitmapFont` instance and use the `BitmapFontLoader.load(URL, URL)` to create it. Note: I have used files here, but the usual practise is to create a JAR file with the required files and reference them there. I've done it this way to (hopefully) give clarity.

Next we create a `BitmapText` instance, specifying the `BitmapFont` instance we'll be using (the "false" attribute states that we DON'T want this to be right-to-left??).

Next we specify the `Rectangle` (this is a `com.jmex.angelfont.Rectangle` by the way, not an AWT one) that contains the text. The width is handy here because text wrapping has been taken care of.

Now we specify the size of the font on the screen using `text.setSize(16)`. I haven't had a good play with this. I'm setting the size to the same as the size selected in the BM Font Generator. It will scale up or down if you wish. I currently prefer to download different font files/textures for each size, but I haven't given it a thorough testing yet.

With the size set, we can now specify the colour, and even add alpha control if we want. Nice! I've gone for green in this example. I'm also set `LightCombineMode` to Off so that we're not affected by any light states.

Then we set the text, and call `txt.update()` (which I guess remaps the characters from the png file). If you change the text at any time, you're gonna' need to call this `update()` method.

Finally, we add this text to an "ORTHO" queue. This means (as far as I know so far) is rendered last, meaning it should always be on top of everything else. This isn't actually necessary in this example, as the `BitmapText` instance is already a node with `QUEUE_ORTHO` specified. However, I've added it because I can imagine that you might want to group up your `BitmapText` nodes. I also use this node to position where the text goes.

Hooray, we're there. With any luck, you should now be able to run this and see the text on the screen.

<img src='http://farm2.static.flickr.com/1300/4680228586_cf687e1d31_b.jpg' alt='jme-image1' width='646' height='512' />

By the way, the `BitmapText` node automatically addds a `BlendState` for you, taking advantage of the alpha you put in the PNG image (if you can remember that far back!). Good eh?

Have fun!