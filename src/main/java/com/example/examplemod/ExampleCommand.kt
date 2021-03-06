package com.example.examplemod

//#if MC<=11202
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer

class ExampleCommand : CommandBase() {
    //#if MC<=10809
    override fun getCommandName() = "example"

    override fun getCommandUsage(sender: ICommandSender?) = "/example - open example gui"

    override fun getRequiredPermissionLevel() = 0

    override fun processCommand(sender: ICommandSender?, args: Array<String>) {
       ExampleMod.gui = KtTestGui()
    }
    //#else
    //$$ override fun getName() = "example"
    //$$
    //$$ override fun getUsage(sender: ICommandSender) = "/example - open example gui"
    //$$
    //$$ override fun getRequiredPermissionLevel() = 0
    //$$
    //$$ override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
    //$$     ExampleMod.gui = KtTestGui()
    //$$ }
    //#endif
}
//#else
//$$ import com.mojang.brigadier.CommandDispatcher
//$$ import com.mojang.brigadier.context.CommandContext
//#if FABRIC
//$$ import net.minecraft.server.command.CommandSource
//$$ import net.minecraft.server.command.ServerCommandSource
//$$ import net.minecraft.server.command.CommandManager
//$$
//$$ object ExampleCommand {
//$$     fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
//$$         dispatcher.register(CommandManager.literal("example")
//#else
//$$ import net.minecraft.command.CommandSource
//$$ import net.minecraft.command.Commands
//$$
//$$ object ExampleCommand {
//$$     fun register(dispatcher: CommandDispatcher<CommandSource?>) {
//$$         dispatcher.register(Commands.literal("example")
//#endif
//$$             .requires { it.hasPermissionLevel(0) }
//$$             .executes {
//$$                 ExampleMod.gui = KtTestGui()
//$$                 1
//$$             })
//$$     }
//$$ }
//#endif