package com.example.testbank.base

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class BaseGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        // https://github.com/BigBadaboom/androidsvg
        // 글라이드에 svg 렌더링 추가
        // registry.register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
        //     .append(InputStream::class.java, SVG::class.java, SvgDecoder())
    }

    // override fun isManifestParsingEnabled(): Boolean =
    //     false
}