package com.brandon3055.projectintelligence.client.gui.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author brandon3055
 */
public class MDReference extends javax.swing.JFrame {

    public MDReference() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMinimum());
        jTextArea1.setTabSize(2);
        jTextArea1.setEditable(false);
        jTextArea1.setCaretPosition(0);
    }

    private void initComponents() {

        jCheckBox1 = new JCheckBox();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MD Tag Reference");

        jCheckBox1.setText("Always on top");
        jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("## General notes ##\nWhen setting the size for an md tag most tags support both an exact size in pixels\nor a screen relative size (thats 1 to 100% of the screen width) Screen relative size\nis defined by specyfying a number wetween 1 and 100 followed by % e.g. 50%\n\nColours can be specified as ether a hex value starting with 0x or # \ne.g 0x35C3B7 or #35C3B7\n\nSome tags support padding. Padding does different things with different tags. \nIn the case of links it sets the size of the margine around the link or the button size\nif the link is in button rendering mode.\n\n## Formatting Tags ###########################################################\n\n§colour[0xRRGGBB] or §colour[#RRGGBB]\nThis tag applies a colour override to the next line.\n\n§align:left / §align:center / §align:right\nSets page content alignment\nThis applies to all following lines or until the next align tag.\n\nHeadings\nHeadings can be defined one of 2 ways.\n# heading 1 (Largest)\n## heading 2\n### heading 3\n#### heading 4\n##### heading 5 \n###### heading 6 (Smallest)\n\nHeading 1\n========= \n\nHeading 2\n---------\n\n## Stack #####################################################################\n\nFormat:\n§stack[<item id>]{<paramaters>}\n\nExample:\n§stack[draconicevolution:draconium_ore,1,1]{size:18,draw_slot:true,alt_hover:Tool Tip} \n\nParameters:\nsize\t\t\tRender size. Supports exact pixel size or screen relative size\ndraw_slot\t\tEnables the inventory slot background [values: true, false (default false)]\ndraw_hover\t\tEnables the inventory slot background [values: true, false (default false)]\nalt_hover\t\tAllows you to override the default stack tool tip with something custom\n\nNote: You can display all varients of a stack by setting its damage to the wildcard value 32767\n\n## Recipe ####################################################################\n\nFormat:\n§recipe[<item id>]{<paramaters>}\n\nExample:\n§recipe[draconicevolution:draconium_chest]{border_colour:0xc6c6c6}\n\nParameters:\nborder_colour\t\t\tSets the border/background colour\nborder_colour_hover\tSets the border/background colour while the cursor is over the recipe\npadding\t\t\t\tSets a badding value for all 4 sides (the padding aria is part of the border/\n\t\t\t\t\t\tbackground area)\nleft_pad\t\t\t\tSets the left padding\nright_pad\t\t\t\tSets the right padding\ntop_pad\t\t\t\tSets the top padding\nbottom_pad\t\t\tSets the bottom padding\nspacing\t\t\t\tSets the spacing between recipies if there is more than 1 recipe for this stack\n\nNote: it is recommended to leave the background colour the default value 0xc6c6c6 as\nmost recipe's are designed to be rendered in a standard vanilla ui with that colour as the background.\nDefault padding and spacing have also been provided but feel free to tweak these.\n\nAlso note the padding/border feature should not be required with the latest versions of JEI as the\nbuilt in vanilla style recipe background jei now adds removes the need for a border.\n\n## Link ######################################################################\nLinks can point to web pages or other PI documentation pages. To point to a doc page\nsimply use the pages Unique Resourse Identifier or \"URI\" as the target. To get a page \nURI right click on the page ether in game or in the editor page tree and select \n\"Copy Page URI\" that will copy the URI to your clipboard.\n\nFormat:\n§link[<link target>]{<paramaters>}\n\nExamples:\n§link[draconicevolution:players]\n§link[www.google.com]{padding:3,link_style:vanilla}\n\nParameters:\ncolour\t\t\t\t\tSets the text colour or fill colour in solid render mode\ncolour_hover  \t\t\tSets the text colour or fill colour while the cursor is over the link in solid render mode\nborder_colour\t\t\tSets the border colour in solid render mode\nborder_colour_hover  \tSets the colour while the cursor is over the link in solid render mode\npadding\t\t\t\tSets a badding value for all 4 sides\nleft_pad\t\t\t\tSets the left padding\nright_pad\t\t\t\tSets the right padding\ntop_pad\t\t\t\tSets the top padding\nbottom_pad\t\t\tSets the bottom padding\nlink_style\t\t\t\tSets the render style [values: text, vanilla, solid (default text)]\nalt_text\t\t\t\t\tAllows you to change the display text\ntooltip\t\t\t\t\tAllows you to add a hover tool tip\n\nNote: when you change the render style in the link editor ui default colours will be loaded\nfor the selected render style. Feel free to change these.\n\nAlso note the text colour for vanilla button style links matches the\ncolour used by vanilla buttons and cannot be changed.\nSolid style buttons use the current paragraph text colour and so can be changed\nusing the §colour[0xRRGGBB] tag.\n\n## Image ####################################################################\nAllows you to display an image. This feature only supports images linked via a direct web URL\nImages can be hosted wherever you choose as long as its a stable location where they \nare not likely to get randomly deleted as long as this documentation requires them.\n\nFormat:\n§img[<Image URL>]{<paramaters>}\n\nExample:\n§img[http://ss.brandon3055.com/iqx38ra.jpg]{border_colour:0x0,border_colour_hover:0x0,width:18} \n\nParameters:\nborder_colour\t\t\tSets the border colour in solid render mode\nborder_colour_hover\tSets the colour while the cursor is over the link in solid render mode\npadding\t\t\t\tSets a badding value for all 4 sides\nleft_pad\t\t\t\tSets the left padding\nright_pad\t\t\t\tSets the right padding\ntop_pad\t\t\t\tSets the top padding\nbottom_pad\t\t\tSets the bottom padding\nwidth\t\t\t\t\tThe width of the image in pixels or screen relative size\nheight\t\t\t\t\tThe height of the image in pixels (recommend setting width OR height not both)\nhover\t\t\t\t\tAllows you to add a hover tool tip\nlink_to\t\t\t\t\tAllows you to use the image as a link\n\n## Entity #####################################################################\nThis tag allows you to render almost any entity on the page.\nThis includes players and supports armor and held items for all biped model entities.\n\nFormat:\n§entity[<entity id or player name>]{<paramaters>}\n\nExamples:\n§entity[minecraft:pig]{size:18,scale:1.0,tooltip:\"Its a pig!\",track_mouse:true} \n§entity[player:brandon3055]{size:64,scale:1.0,track_mouse:true,main_hand:\"draconicevolution:draconic_sword,1,0,{Energy:16000000}\",off_hand:\"minecraft:shield\"}\n\nParamaters:\nsize\t\t\tSets the size of the screen element and the entity scale should update to match\nx_offset\t\t\tAllows you to offset the x position of the entity\ny_offset\t\t\tAllows you to offset the y position of the entity\nrotate_speed\tSets the rotation speed of the entity\nrotation\t\t\tSets a static rotation angle that is applied if the rotation speed is 0\nscale\t\t\tAllows you to adjust the scale of the entity independently of the element size\ntooltip\t\t\tAllows you to set a hover tool tip\ntrack_mouse\tWhen true the entity will pay close attention to your mouse cursor\ndraw_name\tDraws the player name tag (Only for players)\nanimate\t\tThis is an experimental feature that may not work with some modded entities.\n\t\t\t\twhen enabled this will enable the entities living animation. (results will vary from entity to entity)\nmain_hand\t\tStack in main hand\noff_hand\t\tStack in off hand\nhead\t\t\tStack in head armor slot\nchest\t\t\tStack in chest armor slot\nlegs\t\t\tStack in leggs armor slot\nboots\t\t\tStack in boots armor slot\n\nYou can do some interesting things with x offset and scale. For example you can make the emement\ntiny by setting its size to 1 but compensate with scale so the entity is still a reasonable size.\nThen use offsets to place the entity in some other location on the screen.\n\nThe other use for offsets and scale is to allow you to compensate for odly sized entities that just\ndont display render correctly by default.\n\n## Rule ######################################################################\n\nExamples:\n§rule{colour:0xFF0000,height:5,top_pad:2,bottom_pad:2,width:100%}\n\nParamaters:\ncolour  Sets the colour of the rule\nborder_colour  \t(optional) sets a colour for the border of the rule \n                \t\t(the border is the (usually transparent) padding area around the rule)\nwidth\t\t\tThe width of the rule in pixels or screen relative size\nheight \t\t\tThe height of the rule in pixels\npadding\t\tSets a padding value for all 4 sides\nleft_pad\t\tSets the left padding\nright_pad\t\tSets the right padding\ntop_pad\t\tSets the top padding\nbottom_pad\tSets the bottom padding\n\nNote: by default this element has its top and bottom padding set to 5.\nTo override this simply set the desired padding manually.\n\nAlso note if you leave out the colour this element will still exist\nbut it will be invisible making it usable as a spacer.\n\n## Table ####################################################################\nThis tag allows you to define a simple table that you can use to display \nother xml elements\n\nFormat:\n§table{<paramaters>}\n| :n64: | :-------------------- | :--------- |\n| Cell Content | Cell Content | Cell Content |\n\nThe table layout is very similar to github's markdown tables so go take a look at their doc\nhttps://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#tables\nThere are 2 main differences. These tables to not strip out additional blank\nspace before and after content. You can however use tabs to make your table markdown \nlook nice and neat.\nThese tables also allow you to define a fixed width for a column using\n\"n<width>\" in place of x number of --- (see examples)\n\nExamples:\n§table{width:100%,colour:0x0,border_colour:0xffffff,heading_colour:0xff0000,vert_align:middle,padding:2}  \n| No. \t| ###### Description \t| ###### Tag \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|\n| :n25: \t| :-------------------- \t\t| :n80: \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|\n| 1 \t\t| Stone Block \t\t\t| §stack[minecraft:stone]{size:18} \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|\n| 2 \t\t| A Pig \t\t\t\t\t| §entity[minecraft:pig]{size:18,rotate_speed:1}  \t\t\t\t\t\t\t\t\t\t\t\t\t\t|\n| 3 \t\t| An Image \t\t\t\t| §img[http://ss.brandon3055.com/045f1]{border_colour:0x0,border_colour_hover:0x0,width:100%} \t|\n| 4 \t\t| A link \t\t\t\t\t| §link[www.googe.com]{colour:0xE0E0E0,colour_hover:0xFFFFA0,padding:3,link_style:vanilla} \t\t|\n\nParamaters:\nwidth \t\t\tSets the width of the table\nheight\t\t\tSets the minimum height for the table rows\nborder_colour\tSets the colour of the table lines\nheading_colour\tSets the background colour for the heading row\ncolour\t\t\tSets the background colour for the table cells\nrender_cells\tCan be set to false to disable rendering of the table lines\nvert_align\t\tSets the vertical alignment of the cell content.\npadding\t\tSets a padding value for the cell content\nleft_pad\nright_pad\ntop_pad\nbottom_pad\n\nNote: The heading row can be completely omitted if not needed.\nAlso note that by setting render_cells to false the table can be very useful\nfor simply positioning content on the page without the table being visible.\n\n## XML Table ####################################################################\nThe XML table has the exact same tag as a regular table and accepts all the same \nparameters. But the actual table content is defined using XML which allows you \nto build much more complex tables as it allows multiple lines per table cell.\n\nAs mentioned all of the tag parameters are the same but with xml tables the padding, \ncolour and alignment parameters just set default values. Every row or cell\nin the table can be given its own alignment, padding and colour using the xml format.\n\nBellow is an example xml table.\nThe deliminator you would use to define the column widths in a basic table is'\nreplaced by the column_layout attribute in the table element.\nThe number of comma separated values specified must match the number of columns in the table\nan integer value defines a fixed column width. Adding a * to the end makes it \na dynamic width.\n\nrow/cell padding is specified using a single padding attribute e.g. padding=\"5,10,5,10\"\nValid formats are \"n\", \"n,n\", \"n,n,n\" or \"n,n,n,n\" Where n is the padding value as an integer.\nThe values specified apply to:\n\"<all sides>\", \"<top & bottom>,<right & left>\",\n\"<top>,<right & left>,<bottom>\" or \"<top>,<right>,<bottom>,<left>\"\n\nAlignment uses an \"align\" tag and can contain any combination of \ntop, middle or bottom and left, center or right separated by a space, dash or nothing \ne.g align=\"top left\" or align=\"bottom-right\" or align=\"centermiddle\"\n\nThe colour tag is pretty self explanitory\ncolour=\"0xRRGGBB\" or colour=\"#RRGGBB\" or colour=\"red,green,blue\"\n\n§table{width:100%,colour:0x0,border_colour:0xffffff,heading_colour:0xff0000} \n<table column_layout=\"25,1*,80\">\n<tr padding=\"3,0,0,0\" align=\"middle center\" colour=\"0xFF00FF\">\n\t<td>No.</td>   \n\t<td>###### Description </td> \n\t<td>###### Tag</td>\n</tr>\n<tr padding=\"2\" align=\"middle\">\n\t<td align=\"center\">1</td>\n\t<td>Stone Block</td>\n\t<td align=\"center\">§stack[minecraft:stone]{size:18} </td>\n</tr>\n<tr padding=\"2\" align=\"middle\">\n\t<td align=\"center\">2</td>\n\t<td>A Pig</td> \n\t<td align=\"center\" padding=\"2\">§entity[minecraft:pig]{size:18,rotate_speed:1}</td> \n</tr>\n<tr padding=\"2\" align=\"middle\">\n\t<td align=\"center\">3</td>\n\t<td colour=\"0xFF9040\">An Image</td> \n\t<td align=\"center\" padding=\"2\">§img[http://ss.brandon3055.com/045f1]{border_colour:0x0,border_colour_hover:0x0,width:100%}</td>\n</tr>\n<tr padding=\"2\" align=\"middle\">\t\n\t<td align=\"center\" colour=\"0xFFFF00\">4</td>\n\t<td>A Link! (Finally!)</td>\n\t<td align=\"center\" padding=\"2\">§link[www.googe.com]{colour:0xE0E0E0,colour_hover:0xFFFFA0,padding:3,link_style:vanilla}</td>\n</tr>\n</table> \n\n\nBellow is an example of nested tables\n§table{width:100%,colour:0x0,border_colour:0xffffff,heading_colour:0xff0000,render_cells:true} \n<table column_layout=\"25,1*,80\">\n<tr padding=\"3,0,0,0\" align=\"middle center\" colour=\"0xFF0000\">\n\t<td>No.</td>   \n\t<td>###### Description </td> \n\t<td>###### Tag</td>\n</tr>\n<tr padding=\"2\" align=\"middle\">\n\t<td align=\"center\">1</td>\n\t<td>Stone Block</td>\n\t<td align=\"center\">§stack[minecraft:stone]{size:18} </td>\n</tr>\n<tr padding=\"2\" align=\"middle\">\n\t<td align=\"center\">2</td>\n\t<td>A Pig</td> \n\t<td align=\"center\" padding=\"2\">§entity[minecraft:pig]{size:18,rotate_speed:1}</td> \n</tr>\n<tr padding=\"2\" align=\"middle\">\n\t<td align=\"center\">3</td>\n\t<td>An Image</td> \n\t<td align=\"center\" padding=\"2\">§img[http://ss.brandon3055.com/045f1]{border_colour:0x0,border_colour_hover:0x0,width:100%}</td>\n</tr>\n<tr padding=\"2\" align=\"middle\">\n\t<td align=\"center\" colour=\"0xFF0000\">4</td>\n\t<td>\n\t\tSo...... Yea.... This is a thing now....\n\t\t§table{width:100%,colour:0x0,border_colour:0xffffff,heading_colour:0x00FF00,render_cells:true} \n\t\t<table column_layout=\"25,1*,80\">\n\t\t<tr padding=\"3,0,0,0\" align=\"middle center\" colour=\"0x00FF00\">\n\t\t\t<td>No.</td>   \n\t\t\t<td>###### Description </td> \n\t\t\t<td>###### Tag</td>\n\t\t</tr>\n\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t<td align=\"center\">1</td>\n\t\t\t<td colour=\"0x505050\">Stone Block</td>\n\t\t\t<td align=\"center\">§stack[minecraft:stone]{size:18} </td>\n\t\t</tr>\n\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t<td align=\"center\">2</td>\n\t\t\t<td>A Pig</td> \n\t\t\t<td align=\"center\" padding=\"2\">§entity[minecraft:pig]{size:18,rotate_speed:1}</td> \n\t\t</tr>\n\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t<td align=\"center\">3</td>\n\t\t\t<td>An Image</td> \n\t\t\t<td align=\"center\" padding=\"2\">§img[http://ss.brandon3055.com/045f1]{border_colour:0x0,border_colour_hover:0x0,width:100%}</td>\n\t\t</tr>\n\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t<td align=\"center\" colour=\"0x00FF00\">4</td>\n\t\t\t<td>\n\t\t\t\tSo...... Yea.... This is a thing now....\n\t\t\t\t§table{width:100%,colour:0x0,border_colour:0xffffff,heading_colour:0xff0000,render_cells:true} \n\t\t\t\t<table column_layout=\"25,1*,80\">\n\t\t\t\t<tr padding=\"3,0,0,0\" align=\"middle center\" colour=\"0x0000FF\">\n\t\t\t\t\t<td>No.</td>   \n\t\t\t\t\t<td colour=\"0x00FFFF\">###### Description </td> \n\t\t\t\t\t<td>###### Tag</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t<td align=\"center\">1</td>\n\t\t\t\t\t<td>Stone Block</td>\n\t\t\t\t\t<td align=\"center\">§stack[minecraft:stone]{size:18} </td>\n\t\t\t\t</tr>\n\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t<td align=\"center\">2</td>\n\t\t\t\t\t<td>A Pig</td> \n\t\t\t\t\t<td align=\"center\" padding=\"2\">§entity[minecraft:pig]{size:18,rotate_speed:1}</td> \n\t\t\t\t</tr>\n\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t<td align=\"center\">3</td>\n\t\t\t\t\t<td colour=\"0x4090FF\">An Image</td> \n\t\t\t\t\t<td align=\"center\" padding=\"2\">§img[http://ss.brandon3055.com/045f1]{border_colour:0x0,border_colour_hover:0x0,width:100%}</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t<td align=\"center\" colour=\"0x0000FF\">4</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\tSo...... Yea.... This is a thing now....\n\t\t\t\t\t\t§table{width:100%,colour:0x0,border_colour:0xffffff,heading_colour:0xff0000,render_cells:true} \n\t\t\t\t\t\t<table column_layout=\"25,1*,80\">\n\t\t\t\t\t\t<tr padding=\"3,0,0,0\" align=\"middle center\" colour=\"0xFF00FF\">\n\t\t\t\t\t\t\t<td>No.</td>   \n\t\t\t\t\t\t\t<td>###### Description </td> \n\t\t\t\t\t\t\t<td>###### Tag</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t\t\t<td align=\"center\">1</td>\n\t\t\t\t\t\t\t<td>Stone Block</td>\n\t\t\t\t\t\t\t<td align=\"center\">§stack[minecraft:stone]{size:18} </td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t\t\t<td align=\"center\">2</td>\n\t\t\t\t\t\t\t<td>A Pig</td> \n\t\t\t\t\t\t\t<td align=\"center\" padding=\"2\">§entity[minecraft:pig]{size:18,rotate_speed:1}</td> \n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t\t\t<td align=\"center\">3</td>\n\t\t\t\t\t\t\t<td colour=\"0xFF9040\">An Image</td> \n\t\t\t\t\t\t\t<td align=\"center\" padding=\"2\">§img[http://ss.brandon3055.com/045f1]{border_colour:0x0,border_colour_hover:0x0,width:100%}</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr padding=\"2\" align=\"middle\">\n\t\t\t\t\t\t\t<td align=\"center\" colour=\"0xFFFF00\">4</td>\n\t\t\t\t\t\t\t<td>A Link! (Finally!)</td>\n\t\t\t\t\t\t\t<td align=\"center\" padding=\"2\">§link[www.googe.com]{colour:0xE0E0E0,colour_hover:0xFFFFA0,padding:3,link_style:vanilla}</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t</table> \n\t\t\t\t\t</td>\n\t\t\t\t\t<td align=\"center\" padding=\"2\">§link[www.googe.com]{colour:0xE0E0E0,colour_hover:0xFFFFA0,padding:3,link_style:vanilla}</td>\n\t\t\t\t</tr>\n\t\t\t\t</table>\n\t\t\t</td> \n\t\t\t<td align=\"center\" padding=\"2\">§link[www.googe.com]{colour:0xE0E0E0,colour_hover:0xFFFFA0,padding:3,link_style:vanilla}</td>\n\t\t</tr>\n\t\t</table>\n\t</td>\n\t<td align=\"center\" padding=\"2\">§link[www.googe.com]{colour:0xE0E0E0,colour_hover:0xFFFFA0,padding:3,link_style:vanilla}</td>\n</tr>\n</table>");
        jScrollPane1.setViewportView(jTextArea1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void jCheckBox1ActionPerformed(ActionEvent evt) {                                           
        setAlwaysOnTop(jCheckBox1.isSelected());
    }

    public JCheckBox jCheckBox1;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
}