# For info on the mod go to either
* [modrinth](https://modrinth.com/mod/enhanced-explosives)
* [curseforge](https://www.curseforge.com/minecraft/mc-mods/enhanced-explosives)
* in-game wiki (guidebook & patchouli)

# How to run / build the mod in an IDE
* git clone the project
* run the _runData_ gradle task, it generates the item & block models
  * the data generation only works in versions =>1.20.1, as I didn't backport it. If you want to build the 
    mod for versions before that, you can run the _runData_ gradle task in the 1.20.1 branch and switch back to the 
    branch for your desired version
* run _runClient_ to start a minecraft session, or run _jar_ to generate a .jar file of the mod (located at _.
  /build/reobfJar/output.jar_)