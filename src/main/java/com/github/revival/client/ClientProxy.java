package com.github.revival.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.github.revival.Revival;
import com.github.revival.client.model.ModelDeadAnu;
import com.github.revival.client.model.ModelFailuresaurus;
import com.github.revival.client.model.ModelPigBoss;
import com.github.revival.client.model.armor.ModelAncientHelmet;
import com.github.revival.client.model.prehistoric.ModelAlligatorGar;
import com.github.revival.client.model.prehistoric.ModelAllosaurus;
import com.github.revival.client.model.prehistoric.ModelAnkylosaurus;
import com.github.revival.client.model.prehistoric.ModelBrachiosaurus;
import com.github.revival.client.model.prehistoric.ModelCeratosaurus;
import com.github.revival.client.model.prehistoric.ModelCoelacanth;
import com.github.revival.client.model.prehistoric.ModelCompsognathus;
import com.github.revival.client.model.prehistoric.ModelConfuciusornis;
import com.github.revival.client.model.prehistoric.ModelDeinonychus;
import com.github.revival.client.model.prehistoric.ModelDilophosaurus;
import com.github.revival.client.model.prehistoric.ModelDodo;
import com.github.revival.client.model.prehistoric.ModelElasmotherium;
import com.github.revival.client.model.prehistoric.ModelGallimimus;
import com.github.revival.client.model.prehistoric.ModelLiopleurodon;
import com.github.revival.client.model.prehistoric.ModelMammoth;
import com.github.revival.client.model.prehistoric.ModelMosasaurus;
import com.github.revival.client.model.prehistoric.ModelNautilus;
import com.github.revival.client.model.prehistoric.ModelPachycephalosaurus;
import com.github.revival.client.model.prehistoric.ModelPlesiosaurus;
import com.github.revival.client.model.prehistoric.ModelPteranodon;
import com.github.revival.client.model.prehistoric.ModelQuagga;
import com.github.revival.client.model.prehistoric.ModelSarcosuchus;
import com.github.revival.client.model.prehistoric.ModelSmilodon;
import com.github.revival.client.model.prehistoric.ModelSpinosaurus;
import com.github.revival.client.model.prehistoric.ModelStegosaurus;
import com.github.revival.client.model.prehistoric.ModelSturgeon;
import com.github.revival.client.model.prehistoric.ModelTerrorBird;
import com.github.revival.client.model.prehistoric.ModelTriceratops;
import com.github.revival.client.model.prehistoric.ModelTyrannosaurus;
import com.github.revival.client.model.prehistoric.ModelVelociraptor;
import com.github.revival.client.model.prehistoric.alternate.ModelFlyingConfuciusornis;
import com.github.revival.client.renderer.entity.RenderAnuEffect;
import com.github.revival.client.renderer.entity.RenderAnubite;
import com.github.revival.client.renderer.entity.RenderBirdEgg;
import com.github.revival.client.renderer.entity.RenderBones;
import com.github.revival.client.renderer.entity.RenderDeadAnu;
import com.github.revival.client.renderer.entity.RenderDinoEgg;
import com.github.revival.client.renderer.entity.RenderFPZ;
import com.github.revival.client.renderer.entity.RenderFailuresaurus;
import com.github.revival.client.renderer.entity.RenderFish;
import com.github.revival.client.renderer.entity.RenderFlyingPrehistoric;
import com.github.revival.client.renderer.entity.RenderGlowingPrehistoric;
import com.github.revival.client.renderer.entity.RenderJavelin;
import com.github.revival.client.renderer.entity.RenderPigBoss;
import com.github.revival.client.renderer.entity.RenderPlayerCapes;
import com.github.revival.client.renderer.entity.RenderPrehistoric;
import com.github.revival.client.renderer.entity.RenderQuagga;
import com.github.revival.client.renderer.entity.RenderSentryPigman;
import com.github.revival.client.renderer.entity.RenderStoneboard;
import com.github.revival.client.renderer.entity.RenderTarSlime;
import com.github.revival.client.renderer.entity.RenderTerrorBird;
import com.github.revival.client.renderer.entity.RenderToyBall;
import com.github.revival.client.renderer.entity.RenderToyScratchingPost;
import com.github.revival.client.renderer.entity.RenderToyTetheredLog;
import com.github.revival.client.renderer.item.ItemAncientClocRender;
import com.github.revival.client.renderer.item.ItemFigurineRenderer;
import com.github.revival.client.renderer.item.ItemRenderAnuTotem;
import com.github.revival.client.renderer.item.ItemRenderAnubite;
import com.github.revival.client.renderer.item.ItemRenderSarcophagus;
import com.github.revival.client.renderer.item.ItemRenderTileEntity;
import com.github.revival.client.renderer.item.ItemToyBallRender;
import com.github.revival.client.renderer.item.ItemToyScratchingPostRender;
import com.github.revival.client.renderer.item.ItemToyTetheredBallRender;
import com.github.revival.client.renderer.item.ItemVaseAmphoraRenderer;
import com.github.revival.client.renderer.item.ItemVaseKylixRenderer;
import com.github.revival.client.renderer.item.ItemVaseVoluteRenderer;
import com.github.revival.client.renderer.particle.BubbleFX;
import com.github.revival.client.renderer.particle.DeathOrbFX;
import com.github.revival.client.renderer.particle.HeartFX;
import com.github.revival.client.renderer.tileentity.RenderFeeder;
import com.github.revival.client.renderer.tileentity.RenderTNClock;
import com.github.revival.client.renderer.tileentity.TileEntityAncientChestRender;
import com.github.revival.client.renderer.tileentity.TileEntityAnuTotemRender;
import com.github.revival.client.renderer.tileentity.TileEntityAnubiteStatueRender;
import com.github.revival.client.renderer.tileentity.TileEntityCultivateRenderer;
import com.github.revival.client.renderer.tileentity.TileEntityFigurineRenderer;
import com.github.revival.client.renderer.tileentity.TileEntitySarcophagusRender;
import com.github.revival.client.renderer.tileentity.TileEntityVaseRenderer;
import com.github.revival.server.ServerProxy;
import com.github.revival.server.block.FABlockRegistry;
import com.github.revival.server.block.entity.TileEntityAncientChest;
import com.github.revival.server.block.entity.TileEntityAnuTotem;
import com.github.revival.server.block.entity.TileEntityAnubiteStatue;
import com.github.revival.server.block.entity.TileEntityCultivate;
import com.github.revival.server.block.entity.TileEntityFigurine;
import com.github.revival.server.block.entity.TileEntitySarcophagus;
import com.github.revival.server.block.entity.TileEntityTimeMachine;
import com.github.revival.server.block.entity.TileEntityVase;
import com.github.revival.server.entity.EntityAncientJavelin;
import com.github.revival.server.entity.EntityAnuEffect;
import com.github.revival.server.entity.EntityDinoEgg;
import com.github.revival.server.entity.EntityJavelin;
import com.github.revival.server.entity.EntityStoneboard;
import com.github.revival.server.entity.mob.EntityAlligatorGar;
import com.github.revival.server.entity.mob.EntityAllosaurus;
import com.github.revival.server.entity.mob.EntityAnkylosaurus;
import com.github.revival.server.entity.mob.EntityAnu;
import com.github.revival.server.entity.mob.EntityAnuDead;
import com.github.revival.server.entity.mob.EntityAnubite;
import com.github.revival.server.entity.mob.EntityBones;
import com.github.revival.server.entity.mob.EntityBrachiosaurus;
import com.github.revival.server.entity.mob.EntityCeratosaurus;
import com.github.revival.server.entity.mob.EntityCoelacanth;
import com.github.revival.server.entity.mob.EntityCompsognathus;
import com.github.revival.server.entity.mob.EntityConfuciusornis;
import com.github.revival.server.entity.mob.EntityDeinonychus;
import com.github.revival.server.entity.mob.EntityDilophosaurus;
import com.github.revival.server.entity.mob.EntityDodo;
import com.github.revival.server.entity.mob.EntityElasmotherium;
import com.github.revival.server.entity.mob.EntityFailuresaurus;
import com.github.revival.server.entity.mob.EntityFriendlyPigZombie;
import com.github.revival.server.entity.mob.EntityGallimimus;
import com.github.revival.server.entity.mob.EntityGastornis;
import com.github.revival.server.entity.mob.EntityKelenken;
import com.github.revival.server.entity.mob.EntityLiopleurodon;
import com.github.revival.server.entity.mob.EntityMammoth;
import com.github.revival.server.entity.mob.EntityMosasaurus;
import com.github.revival.server.entity.mob.EntityNautilus;
import com.github.revival.server.entity.mob.EntityPachycephalosaurus;
import com.github.revival.server.entity.mob.EntityPhorusrhacos;
import com.github.revival.server.entity.mob.EntityPlesiosaurus;
import com.github.revival.server.entity.mob.EntityPterosaur;
import com.github.revival.server.entity.mob.EntityQuagga;
import com.github.revival.server.entity.mob.EntitySarcosuchus;
import com.github.revival.server.entity.mob.EntitySentryPigman;
import com.github.revival.server.entity.mob.EntitySmilodon;
import com.github.revival.server.entity.mob.EntitySpinosaurus;
import com.github.revival.server.entity.mob.EntityStegosaurus;
import com.github.revival.server.entity.mob.EntitySturgeon;
import com.github.revival.server.entity.mob.EntityTarSlime;
import com.github.revival.server.entity.mob.EntityTerrorBird;
import com.github.revival.server.entity.mob.EntityTitanis;
import com.github.revival.server.entity.mob.EntityTriceratops;
import com.github.revival.server.entity.mob.EntityTyrannosaurus;
import com.github.revival.server.entity.mob.EntityVelociraptor;
import com.github.revival.server.entity.mob.projectile.EntityBirdEgg;
import com.github.revival.server.entity.mob.test.EntityFishBase;
import com.github.revival.server.entity.mob.test.EntityNewPrehistoric;
import com.github.revival.server.entity.toy.EntityToyBall;
import com.github.revival.server.entity.toy.EntityToyScratchingPost;
import com.github.revival.server.entity.toy.EntityToyTetheredLog;
import com.github.revival.server.handler.EventNewMenu;
import com.github.revival.server.handler.EventOverlay;
import com.github.revival.server.handler.FossilClientEvents;
import com.github.revival.server.item.FAItemRegistry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;


public class ClientProxy extends ServerProxy {
	private static final ModelAncientHelmet helmetModel = new ModelAncientHelmet(1.0f);
	// private JsonTabulaModel velociraptor = ModelHelper.parseModelFromJson(Revival.class.getResourceAsStream("/assets/fossil/models/Velociraptor.json"));

	@Override
	public void init() {
		super.init();
		RenderingRegistry.registerEntityRenderingHandler(EntityTriceratops.class, new RenderPrehistoric(new ModelTriceratops()));
		RenderingRegistry.registerEntityRenderingHandler(EntityVelociraptor.class, new RenderPrehistoric(new ModelVelociraptor()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTyrannosaurus.class, new RenderPrehistoric(new ModelTyrannosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPterosaur.class, new RenderPrehistoric(new ModelPteranodon()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlesiosaurus.class, new RenderPrehistoric(new ModelPlesiosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityMosasaurus.class, new RenderPrehistoric(new ModelMosasaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityStegosaurus.class, new RenderPrehistoric(new ModelStegosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDilophosaurus.class, new RenderPrehistoric(new ModelDilophosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySmilodon.class, new RenderPrehistoric(new ModelSmilodon()));
		RenderingRegistry.registerEntityRenderingHandler(EntityBrachiosaurus.class, new RenderPrehistoric(new ModelBrachiosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityMammoth.class, new RenderPrehistoric(new ModelMammoth()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpinosaurus.class, new RenderPrehistoric(new ModelSpinosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCompsognathus.class, new RenderGlowingPrehistoric(new ModelCompsognathus(), new ResourceLocation("fossil:textures/mob/compsognathus/compsognathus_overlay.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityDodo.class, new RenderPrehistoric(new ModelDodo()));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnkylosaurus.class, new RenderPrehistoric(new ModelAnkylosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPachycephalosaurus.class, new RenderPrehistoric(new ModelPachycephalosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDeinonychus.class, new RenderPrehistoric(new ModelDeinonychus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityGallimimus.class, new RenderPrehistoric(new ModelGallimimus()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySarcosuchus.class, new RenderPrehistoric(new ModelSarcosuchus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityAllosaurus.class, new RenderPrehistoric(new ModelAllosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCoelacanth.class, new RenderFish(new ModelCoelacanth()));
		RenderingRegistry.registerEntityRenderingHandler(EntityAlligatorGar.class, new RenderFish(new ModelAlligatorGar()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySturgeon.class, new RenderFish(new ModelSturgeon()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLiopleurodon.class, new RenderPrehistoric(new ModelLiopleurodon()));
		RenderingRegistry.registerEntityRenderingHandler(EntityElasmotherium.class, new RenderPrehistoric(new ModelElasmotherium()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCeratosaurus.class, new RenderPrehistoric(new ModelCeratosaurus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityConfuciusornis.class, new RenderFlyingPrehistoric(new ModelConfuciusornis(), new ModelFlyingConfuciusornis()));
		RenderingRegistry.registerEntityRenderingHandler(EntityGastornis.class, new RenderPrehistoric(new ModelTerrorBird()));
		RenderingRegistry.registerEntityRenderingHandler(EntityKelenken.class, new RenderPrehistoric(new ModelTerrorBird()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPhorusrhacos.class, new RenderPrehistoric(new ModelTerrorBird()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTitanis.class, new RenderPrehistoric(new ModelTerrorBird()));
		RenderingRegistry.registerEntityRenderingHandler(EntityNautilus.class, new RenderFish(new ModelNautilus()));
		RenderingRegistry.registerEntityRenderingHandler(EntityStoneboard.class, new RenderStoneboard());
		RenderingRegistry.registerEntityRenderingHandler(EntityFailuresaurus.class, new RenderFailuresaurus(new ModelFailuresaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnu.class, new RenderPigBoss(new ModelPigBoss(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyPigZombie.class, new RenderFPZ());
		RenderingRegistry.registerEntityRenderingHandler(EntityDinoEgg.class, new RenderDinoEgg(0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJavelin.class, new RenderJavelin());
		RenderingRegistry.registerEntityRenderingHandler(EntityAncientJavelin.class, new RenderJavelin());
		RenderingRegistry.registerEntityRenderingHandler(EntityBones.class, new RenderBones());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnuEffect.class, new RenderAnuEffect());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnubite.class, new RenderAnubite());
		RenderingRegistry.registerEntityRenderingHandler(EntitySentryPigman.class, new RenderSentryPigman());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnuDead.class, new RenderDeadAnu(new ModelDeadAnu(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBirdEgg.class, new RenderBirdEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityTarSlime.class, new RenderTarSlime());
		RenderingRegistry.registerEntityRenderingHandler(EntityQuagga.class, new RenderQuagga(new ModelQuagga(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerrorBird.class, new RenderTerrorBird(new ModelTerrorBird(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityToyBall.class, new RenderToyBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityToyTetheredLog.class, new RenderToyTetheredLog());
		RenderingRegistry.registerEntityRenderingHandler(EntityToyScratchingPost.class, new RenderToyScratchingPost());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.figurineBlock), new ItemFigurineRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.vaseVoluteBlock), new ItemVaseVoluteRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.vaseAmphoraBlock), new ItemVaseAmphoraRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.vaseKylixBlock), new ItemVaseKylixRenderer());
		MinecraftForgeClient.registerItemRenderer(FAItemRegistry.INSTANCE.ancientClock, new ItemAncientClocRender());
		MinecraftForgeClient.registerItemRenderer(FAItemRegistry.INSTANCE.toyBall, new ItemToyBallRender());
		MinecraftForgeClient.registerItemRenderer(FAItemRegistry.INSTANCE.toyTetheredLog, new ItemToyTetheredBallRender());
		MinecraftForgeClient.registerItemRenderer(FAItemRegistry.INSTANCE.toyScratchingPost, new ItemToyScratchingPostRender());

		RenderingRegistry.registerBlockHandler(RenderFeeder.feederRenderID, new RenderFeeder());

		VillagerRegistry.instance().registerVillagerSkin(10, new ResourceLocation("fossil:textures/model/Archaeologist.png"));

		if (Revival.CONFIG.skullOverlay) {
			MinecraftForge.EVENT_BUS.register(new EventOverlay(Minecraft.getMinecraft()));
		}

		TileEntitySpecialRenderer cultivate = new TileEntityCultivateRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCultivate.class, cultivate);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.blockcultivateActive), new ItemRenderTileEntity(cultivate, new TileEntityCultivate()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.blockcultivateIdle), new ItemRenderTileEntity(cultivate, new TileEntityCultivate()));

		TileEntitySpecialRenderer ancChest = new TileEntityAncientChestRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAncientChest.class, ancChest);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.ancientChest), new ItemRenderTileEntity(ancChest, new TileEntityAncientChest()));

		TileEntitySpecialRenderer totem = new TileEntityAnuTotemRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnuTotem.class, totem);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.anuTotem), new ItemRenderAnuTotem(totem, new TileEntityAnuTotem()));

		TileEntitySpecialRenderer anubite = new TileEntityAnubiteStatueRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnubiteStatue.class, anubite);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.anubiteStatue), new ItemRenderAnubite(anubite, new TileEntityAnubiteStatue()));

		TileEntitySpecialRenderer sarcophagus = new TileEntitySarcophagusRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySarcophagus.class, sarcophagus);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.INSTANCE.sarcophagus), new ItemRenderSarcophagus(sarcophagus, new TileEntitySarcophagus()));

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeMachine.class, new RenderTNClock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFigurine.class, new TileEntityFigurineRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVase.class, new TileEntityVaseRenderer());

		MinecraftForge.EVENT_BUS.register(new FossilClientEvents());
		MinecraftForge.EVENT_BUS.register(new RenderPlayerCapes());
		MinecraftForge.EVENT_BUS.register(new EventNewMenu());
	}

	@Override
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		case 0:
			return helmetModel;
		default:
			break;
		}

		return helmetModel;
	}

	@Override
	public void playSound(String soundName) {
		Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147673_a(new ResourceLocation(soundName)));
	}

	@Override
	public void stopSound(String soundName) {
		ISound sound = PositionedSoundRecord.func_147673_a(new ResourceLocation(soundName));
		if(Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound))
			Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
	}

	@Override
	public void spawnAnuParticle(World world, double posX, double posY, double posZ) {
		EntityFX particle1 = new DeathOrbFX(world, posX, posY, posZ, 0, 0, 0);
		Minecraft.getMinecraft().effectRenderer.addEffect(particle1);
	}

	@Override
	public void animate(int animateID) {
	}

	public void calculateChainBuffer(EntityNewPrehistoric entity) {
		entity.chainBuffer.calculateChainSwingBuffer(70, 10, 4, entity);
	}

	public void calculateChainBuffer(EntityFishBase entity) {
		entity.chainBuffer.calculateChainSwingBuffer(70, 10, 4, entity);
	}
	
	public void spawnBubbleParticles(World world, float f, float f1, float f2, double motionX, double motionY, double motionZ){
		Minecraft.getMinecraft().effectRenderer.addEffect(new BubbleFX(world, f, f1, f2, motionX, motionY, motionZ));
	}

	public void spawnPacketHeartParticles(World world, float f, float f1, float f2, double motionX, double motionY, double motionZ){
		Minecraft.getMinecraft().effectRenderer.addEffect(new HeartFX(world, f, f1, f2, motionX, motionY, motionZ));
	}

	public void spawnPacketItemParticles(World world, float f, float f1, float f2, double motionX, double motionY, double motionZ, Item item){
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBreakingFX(world, f, f1, f2, motionX, motionY, motionZ, item, 0));
	}

	public void spawnPacketBlockParticles(World world, float f, float f1, float f2, double motionX, double motionY, double motionZ, Block block){
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBlockDustFX(world, f, f1, f2, motionX, motionY, motionZ, block, 0));
	}
}
