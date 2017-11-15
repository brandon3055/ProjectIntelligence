package com.brandon3055.projectintelligence.client.gui.guielements;

import codechicken.lib.colour.Colour;
import com.brandon3055.brandonscore.client.ResourceHelperBC;
import com.brandon3055.brandonscore.client.gui.modulargui.MGuiElementBase;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiPopUpDialogBase;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiScrollElement;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiBorderedRect;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextFieldDialog;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.projectintelligence.PIHelpers;
import com.brandon3055.projectintelligence.client.PITextures;
import com.brandon3055.projectintelligence.client.StyleHandler;
import com.brandon3055.projectintelligence.client.gui.GuiProjectIntelligence;
import com.brandon3055.projectintelligence.client.gui.PIConfig;
import com.brandon3055.projectintelligence.docdata.DocumentationManager;
import com.brandon3055.projectintelligence.docdata.LanguageManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;
import java.util.function.Supplier;

import static com.brandon3055.projectintelligence.client.StyleHandler.StyleType.*;

/**
 * Created by brandon3055 on 12/08/2017.
 */
public class GuiPIConfig extends GuiPopUpDialogBase<GuiPIConfig> {

    private GuiScrollElement configList;

    protected int colour;
    protected int border;
    protected int textColour;
    protected int hoverBorder;
    protected int hoverColour;
    protected int textColourHover;
    protected boolean vanilla;
    private GuiPartMenu menu;

    public GuiPIConfig(GuiPartMenu menu) {
        super(menu);
        this.menu = menu;
        setSize(200, 250);
        setDragBar(12);
        setCloseOnOutsideClick(false);
    }


    public void reloadConfigProperties() {
        configList.clearElements();
        //Basic Settings
        configList.addElement(new GuiLabel(TextFormatting.UNDERLINE + I18n.format("pi.config.basic_config")).setYSize(12).setShadow(false).setTextColGetter(hovering -> StyleHandler.getInt("user_dialogs." + TEXT_COLOUR.getName())));

        addConfig(new ConfigProperty(this, "pi.config.open_style_settings").setAction(() -> menu.styleEditor.toggleShown(true, 550)).setCloseOnClick(true));

        //Advanced Settings
        configList.addElement(new GuiLabel(TextFormatting.UNDERLINE + I18n.format("pi.config.advanced_config")).setYSize(12).setShadow(false).setTextColGetter(hovering -> StyleHandler.getInt("user_dialogs." + TEXT_COLOUR.getName())));

        addConfig(new ConfigProperty(this, () -> "pi.config.set_pi_language", () -> (LanguageManager.isCustomUserLanguageSet() ? "" : I18n.format("pi.lang.mc_default") + " ") + LanguageManager.LANG_NAME_MAP.get(LanguageManager.getUserLanguage())).setHoverText(I18n.format("pi.config.set_pi_language.info"), TextFormatting.GRAY + I18n.format("pi.config.set_pi_language_note.info")).setAction(this::openLanguageSelector));

        //region Edit Mode Settings
        addConfig(new ConfigProperty(this, () -> "pi.config.edit_mode", () -> PIConfig.editMode() + "").setAction(() -> {
            PIConfig.setEditMode(!PIConfig.editMode());
            PIConfig.save();
            DocumentationManager.checkAndReloadDocFiles();
        }));

        addConfig(new ConfigProperty(this, () -> "pi.config.edit_repo_loc", () -> PIConfig.editingRepoLoc.isEmpty() ? "[Not Set]" : PIConfig.editingRepoLoc).setHoverText(I18n.format("pi.config.edit_repo_loc.info")).setAction(() -> {
            GuiTextFieldDialog dialog = new GuiTextFieldDialog(this, I18n.format("pi.config.edit_repo_select_title"));
            dialog.setXSize(280).setMaxLength(4096);
            dialog.addChild(new StyledGuiRect("user_dialogs").setPosAndSize(dialog));
            dialog.setTitleColour(StyleHandler.getInt("user_dialogs." + StyleHandler.StyleType.TEXT_COLOUR.getName()));
            dialog.setText(PIConfig.editingRepoLoc);
            dialog.addTextConfirmCallback(s -> {
                PIConfig.editingRepoLoc = s;
                PIConfig.save();
                if (PIConfig.editMode()) {
                    DocumentationManager.checkAndReloadDocFiles();
                }
            });
            dialog.showCenter(displayZLevel + 50);
        }));

        addConfig(new ConfigProperty(this, () -> PIConfig.editMode() ? "pi.config.reload_from_disk" : "pi.config.reload_documentation").setHoverText(PIConfig.editMode() ? I18n.format("pi.config.reload_from_disk.info") : I18n.format("pi.config.reload_documentation.info")).setAction(DocumentationManager::checkAndReloadDocFiles));

        if (PIConfig.editMode()) {
            addConfig(new ConfigProperty(this, "pi.config.open_editor").setAction(PIHelpers::displayEditor));
        }

        //endregion

    }

    public void addConfig(ConfigProperty configProperty) {
        configList.addElement(configProperty);
    }

    @Override
    public void addChildElements() {
        childElements.clear();

        //Background Rectangle
        addChild(new StyledGuiRect("user_dialogs").setPosAndSize(this));

        // Window Title
        addChild(new GuiLabel(TextFormatting.UNDERLINE + I18n.format("pi.config.pi_configuration.title"))//
                .setPos(this).setSize(xSize(), 10).translate(4, 3).setTextColGetter(hovering -> StyleHandler.getInt("user_dialogs." + TEXT_COLOUR.getName())).setShadow(false).setAlignment(GuiAlign.CENTER));

        //Close Button
        GuiButton close = new StyledGuiButton("user_dialogs.button_style").setPos(this).translate(xSize() - 14, 3).setSize(11, 11);
        close.setListener((event, eventSource) -> close());
        close.setHoverText(I18n.format("pi.button.close"));
        close.addChild(new GuiTexture(64, 16, 5, 5, PITextures.PI_PARTS).setRelPos(3, 3));
        addChild(close);

        //List Background
        GuiBorderedRect listBackground;
        addChild(listBackground = new GuiBorderedRect().setRelPos(4, 16).setSize(xSize() - 8, ySize() - 20));
        listBackground.setFillColourGetter(hovering -> StyleHandler.getInt("user_dialogs.sub_elements." + COLOUR.getName()));
        listBackground.setBorderColourGetter(hovering -> StyleHandler.getInt("user_dialogs.sub_elements." + BORDER.getName()));

        //Config List
        configList = new GuiScrollElement();
        configList.setRelPos(5, 17).setSize(xSize() - 10, ySize() - 22);
        configList.setStandardScrollBehavior();
        configList.setListMode(GuiScrollElement.ListMode.VERT_LOCK_POS_WIDTH);
        configList.getVerticalScrollBar().setHidden(true);
        configList.setListSpacing(1);

        configList.clearElements();
        reloadConfigProperties();

        addChild(configList);

        if (!GuiProjectIntelligence.devMode) {
            super.addChildElements();
        }
    }

    @Override
    public void reloadElement() {
        super.reloadElement();
    }

    @Override
    public boolean onUpdate() {
        vanilla = StyleHandler.getBoolean("user_dialogs.sub_elements.button_style." + StyleHandler.StyleType.VANILLA_TEXTURE.getName());
        colour = StyleHandler.getInt("user_dialogs.sub_elements.button_style." + StyleHandler.StyleType.COLOUR.getName());
        hoverColour = StyleHandler.getInt("user_dialogs.sub_elements.button_style." + StyleHandler.StyleType.HOVER.getName());
        border = StyleHandler.getInt("user_dialogs.sub_elements.button_style." + StyleHandler.StyleType.BORDER.getName());
        hoverBorder = StyleHandler.getInt("user_dialogs.sub_elements.button_style." + StyleHandler.StyleType.BORDER_HOVER.getName());
        textColour = StyleHandler.getInt("user_dialogs.sub_elements.button_style." + StyleHandler.StyleType.TEXT_COLOUR.getName());
        textColourHover = StyleHandler.getInt("user_dialogs.sub_elements.button_style." + StyleHandler.StyleType.TEXT_HOVER.getName());

        return super.onUpdate();
    }

    @Override
    protected boolean keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            close();
            return true;
        }
        return super.keyTyped(typedChar, keyCode);
    }

    public static class ConfigProperty extends MGuiElementBase<ConfigProperty> {

        private boolean playSound = true;
        private boolean closeOnClick = false;
        private Supplier<String> unlocalizedNameSupplier = null;
        private Supplier<String> valueSupplier = null;
        private Runnable clickAction = null;
        private GuiPIConfig gui;

        public ConfigProperty(GuiPIConfig gui) {
            this.gui = gui;
            setYSize(15);
        }

        public ConfigProperty(GuiPIConfig gui, Supplier<String> unlocalizedNameSupplier) {
            this(gui);
            this.unlocalizedNameSupplier = unlocalizedNameSupplier;
        }

        public ConfigProperty(GuiPIConfig gui, String unlocalizedName) {
            this(gui);
            this.unlocalizedNameSupplier = () -> unlocalizedName;
        }

        public ConfigProperty(GuiPIConfig gui, Supplier<String> unlocalizedNameSupplier, Supplier<String> valueSupplier) {
            this(gui, unlocalizedNameSupplier);
            this.valueSupplier = valueSupplier;
            setYSize(26);
        }

        public ConfigProperty(GuiPIConfig gui, Supplier<String> unlocalizedNameSupplier, Supplier<String> valueSupplier, Runnable clickAction) {
            this(gui, unlocalizedNameSupplier, valueSupplier);
            this.clickAction = clickAction;
        }

        public ConfigProperty setNameSupplier(Supplier<String> unlocalizedNameSupplier) {
            this.unlocalizedNameSupplier = unlocalizedNameSupplier;
            return this;
        }

        public ConfigProperty setValueSupplier(Supplier<String> valueSupplier) {
            this.valueSupplier = valueSupplier;
            setYSize(26);
            return this;
        }

        public ConfigProperty setAction(Runnable clickAction) {
            this.clickAction = clickAction;
            return this;
        }

        public ConfigProperty setCloseOnClick(boolean closeOnClick) {
            this.closeOnClick = closeOnClick;
            return this;
        }

        public ConfigProperty setSilent() {
            this.playSound = false;
            return this;
        }

        @Override
        public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
            if (isMouseOver(mouseX, mouseY)) {
                if (clickAction != null) {
                    clickAction.run();
                }
                if (closeOnClick) {
                    gui.close();
                }
                if (playSound) {
                    mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                }
                return true;
            }
            return super.mouseClicked(mouseX, mouseY, mouseButton);
        }

        @Override
        public void renderElement(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
            boolean mouseOver = isMouseOver(mouseX, mouseY);

            if (gui.vanilla) {
                Colour.glColourARGB(mouseOver ? gui.hoverColour : gui.colour);
                ResourceHelperBC.bindTexture(PITextures.VANILLA_GUI_SQ);
                drawTiledTextureRectWithTrim(xPos(), yPos(), xSize(), ySize(), 4, 4, 4, 4, 0, 128, 256, 128);
                GlStateManager.color(1, 1, 1, 1);
                drawBorderedRect(xPos(), yPos(), xSize(), ySize(), 1, 0, gui.border);
            }
            else {
                drawBorderedRect(xPos(), yPos(), xSize(), ySize(), 1, mouseOver ? gui.hoverColour : gui.colour, mouseOver ? gui.hoverBorder : gui.border);
            }

            if (unlocalizedNameSupplier != null) {
                drawString(fontRenderer, TextFormatting.UNDERLINE + I18n.format(unlocalizedNameSupplier.get()), xPos() + 4, yPos() + 3, gui.textColour);
            }
            if (valueSupplier != null) {
                drawString(fontRenderer, valueSupplier.get(), xPos() + 4, yPos() + 15, gui.textColourHover);
            }

            super.renderElement(minecraft, mouseX, mouseY, partialTicks);
        }
    }


    //Misc helper code
    public void openLanguageSelector() {
        StyledSelectDialog<String> langSelect = new StyledSelectDialog<>(this, "user_dialogs", "Select Language");
        String doTrans = I18n.format("pi.lang.disable_override");
        if (LanguageManager.isCustomUserLanguageSet()) {
            langSelect.addItem(doTrans);
        }
        langSelect.setSelected(LanguageManager.getUserLanguage());
        LanguageManager.ALL_LANGUAGES.forEach(langSelect::addItem);
        langSelect.setSelectionListener(lang -> {
            LanguageManager.setCustomUserLanguage(lang.equals(doTrans) ? null : lang);
            GuiButton.playGenericClick(mc);
            DocumentationManager.checkAndReloadDocFiles();
        });
        langSelect.setCloseOnSelection(true);
        langSelect.showCenter(this.displayZLevel + 50);
    }
}