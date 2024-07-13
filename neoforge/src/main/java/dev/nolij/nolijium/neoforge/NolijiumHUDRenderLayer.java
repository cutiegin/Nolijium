package dev.nolij.nolijium.neoforge;

import dev.nolij.nolijium.common.NolijiumHUD;
import dev.nolij.nolijium.impl.Nolijium;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import org.jetbrains.annotations.NotNull;

public class NolijiumHUDRenderLayer extends NolijiumHUD implements LayeredDraw.Layer {
	
	@Override
	protected boolean isDebugScreenOpen() {
		return Minecraft.getInstance().getDebugOverlay().showDebugScreen();
	}
	
	@Override
	public void render(@NotNull GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
		if (isHidden())
			return;
		
		this.onFrame(guiGraphics);
		
		//noinspection deprecation
		guiGraphics.drawManaged(() -> {
			var linePosY = posY;
			for (var line : lines) {
				if (!line.text.isEmpty()) {
					if (background)
						guiGraphics.fill(
							line.posX - 2, linePosY,
							line.posX + line.width + (Nolijium.config.hudShadow ? 2 : 1), linePosY + LINE_HEIGHT,
							BACKGROUND_COLOUR);
					
					guiGraphics.drawString(
						FONT, line.text,
						line.posX, linePosY + 2,
						TEXT_COLOUR, Nolijium.config.hudShadow);
				}
				
				linePosY += LINE_HEIGHT;
			}
		});
	}
	
}
