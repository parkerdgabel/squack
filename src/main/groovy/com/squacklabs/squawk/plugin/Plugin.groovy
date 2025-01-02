package com.squacklabs.squawk.plugin

import com.squacklabs.squawk.Squack

import java.util.function.Function

class PluginOptions {
     Map options = [:]
 }

interface PluginOption extends Function<PluginOptions, PluginOptions> {
    PluginOptions apply(PluginOptions options)
}

/**
 * Plugin interface for Squack
 *
 * Plugins are used to extend the functionality of Squack
 * by providing additional features or modifying existing ones.
 * @author Parker Lackey
 */
interface Plugin {
    /**
     * Apply the plugin to the given Squack instance
     * @param squack the Squack instance to apply the plugin to
     */
    void apply(Squack squack, PluginOption... options)
}