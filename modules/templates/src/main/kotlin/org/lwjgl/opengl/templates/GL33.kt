/* 
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.opengl.templates

import org.lwjgl.generator.*
import org.lwjgl.opengl.*

val GL33 = "GL33".nativeClassGL("GL33") {
	documentation =
		"""
		The core OpenGL 3.3 functionality. OpenGL 3.3 implementations support revision 3.30 of the OpenGL Shading Language.

		Extensions promoted to core in this release:
		${ul(
			registryLinkTo("ARB", "shader_bit_encoding"),
			registryLinkTo("ARB", "blend_func_extended"),
			registryLinkTo("ARB", "explicit_attrib_location"),
			registryLinkTo("ARB", "occlusion_query2"),
			registryLinkTo("ARB", "sampler_objects"),
			registryLinkTo("ARB", "texture_rgb10_a2ui"),
			registryLinkTo("ARB", "texture_swizzle"),
			registryLinkTo("ARB", "timer_query"),
			registryLinkTo("ARB", "instanced_arrays"),
			registryLinkTo("ARB", "vertex_type_2_10_10_10_rev")
		)}
		"""

	// ARB_blend_func_extended

	IntConstant(
		"""
		Accepted by the {@code src} and {@code dst} parameters of BlendFunc and BlendFunci, and by the {@code srcRGB}, {@code dstRGB}, {@code srcAlpha} and
		{@code dstAlpha} parameters of BlendFuncSeparate and BlendFuncSeparatei.
		""",

		"SRC1_COLOR"..0x88F9,
		"ONE_MINUS_SRC1_COLOR"..0x88FA,
		"ONE_MINUS_SRC1_ALPHA"..0x88FB
	)

	IntConstant(
		"Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv and GetDoublev.",

		"MAX_DUAL_SOURCE_DRAW_BUFFERS"..0x88FC
	)

	void(
		"BindFragDataLocationIndexed",
		"Binds a user-defined varying out variable to a fragment shader color number and index.",

		GLuint.IN("program", "the name of the program containing varying out variable whose binding to modify"),
		GLuint.IN("colorNumber", "the color number to bind the user-defined varying out variable to"),
		GLuint.IN("index", "the index of the color input to bind the user-defined varying out variable to"),
		const..GLcharASCII_p.IN("name", "the name of the user-defined varying out variable whose binding to modify")
	)

	GLint(
		"GetFragDataIndex",
		"Queries the bindings of color indices to user-defined varying out variables.",

		GLuint.IN("program", "the name of the program containing varying out variable whose binding to query"),
		const..GLcharASCII_p.IN("name", "the name of the user-defined varying out variable whose index to query")
	)

	// ARB_occlusion_query2

	IntConstant(
		"Accepted by the {@code target} parameter of BeginQuery, EndQuery, and GetQueryiv.",

		"ANY_SAMPLES_PASSED"..0x8C2F
	)

	// ARB_sampler_objects

	IntConstant(
		"Accepted by the {@code value} parameter of the GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv and GetDoublev functions.",

		"SAMPLER_BINDING"..0x8919
	)

	void(
		"GenSamplers",
		"Generates sampler object names.",

		AutoSize("samplers")..GLsizei.IN("count", "the number of sampler object names to generate"),
		returnValue..GLuint_p.OUT("samplers", "a buffer in which the generated sampler object names are stored")
	)

	void(
		"DeleteSamplers",
		"Deletes named sampler objects.",

		AutoSize("samplers")..GLsizei.IN("count", "the number of sampler objects to be deleted"),
		SingleValue("sampler")..const..GLuint_p.IN("samplers", "an array of sampler objects to be deleted")
	)

	GLboolean(
		"IsSampler",
		"Determines if a name corresponds to a sampler object.",

		GLuint.IN("sampler", "a value that may be the name of a sampler object")
	)

	void(
		"BindSampler",
		"Binds a named sampler to a texturing target.",

		GLuint.IN("unit", "the index of the texture unit to which the sampler is bound"),
		GLuint.IN("sampler", "the name of a sampler")
	)

	val SamplerParameters =
		"""
		GL11#TEXTURE_WRAP_S GL11#TEXTURE_WRAP_T GL12#TEXTURE_WRAP_R GL11#TEXTURE_MIN_FILTER GL11#TEXTURE_MAG_FILTER GL12#TEXTURE_MIN_LOD
		GL12#TEXTURE_MAX_LOD GL14#TEXTURE_LOD_BIAS GL14#TEXTURE_COMPARE_MODE GL14#TEXTURE_COMPARE_FUNC
		"""

	void(
		"SamplerParameteri",
		"Set the integer value of a sampler parameter.",

		GLuint.IN("sampler", "the sampler object whose parameter to modify"),
		GLenum.IN("pname", "the symbolic name of a single-valued sampler parameter", SamplerParameters),
		GLint.IN("param", "the value of {@code pname}")
	)

	void(
		"SamplerParameterf",
		"Float version of #SamplerParameteri().",

		GLuint.IN("sampler", "the sampler object whose parameter to modify"),
		GLenum.IN("pname", "the symbolic name of a single-valued sampler parameter"),
		GLfloat.IN("param", "the value of {@code pname}")
	)

	void(
		"SamplerParameteriv",
		"Pointer version of #SamplerParameteri().",

		GLuint.IN("sampler", "the sampler object whose parameter to modify"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter", "GL11#TEXTURE_BORDER_COLOR $SamplerParameters"),
		const..GLint_p.IN("params", "an array where the value or values of {@code pname} are stored")
	)

	void(
		"SamplerParameterfv",
		"Float version of #SamplerParameteriv().",

		GLuint.IN("sampler", "the sampler object whose parameter to modify"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter"),
		const..GLfloat_p.IN("params", "an array where the value or values of {@code pname} are stored")
	)

	void(
		"SamplerParameterIiv",
		"Pure integer version of #SamplerParameteriv().",

		GLuint.IN("sampler", "the sampler object whose parameter to modify"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter"),
		const..GLint_p.IN("params", "an array where the value or values of {@code pname} are stored")
	)

	void(
		"SamplerParameterIuiv",
		"Unsigned pure integer version of #SamplerParameteriv().",

		GLuint.IN("sampler", "the sampler object whose parameter to modify"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter"),
		const..GLuint_p.IN("params", "an array where the value or values of {@code pname} are stored")
	)

	void(
		"GetSamplerParameteriv",
		"Return the integer value(s) of a sampler parameter.",

		GLuint.IN("sampler", "the name of the sampler object from which to retrieve parameters"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter", "$SamplerParameters, GL11#TEXTURE_BORDER_COLOR"),
		Check(1)..returnValue..GLint_p.OUT("params", "the sampler parameters")
	)

	void(
		"GetSamplerParameterfv",
		"Float version of #GetSamplerParameteriv().",

		GLuint.IN("sampler", "the name of the sampler object from which to retrieve parameters"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter"),
		Check(1)..returnValue..GLfloat_p.OUT("params", "the sampler parameters")
	)

	void(
		"GetSamplerParameterIiv",
		"Pure integer version of #GetSamplerParameteriv().",

		GLuint.IN("sampler", "the name of the sampler object from which to retrieve parameters"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter"),
		Check(1)..returnValue..GLint_p.OUT("params", "the sampler parameters")
	)

	void(
		"GetSamplerParameterIuiv",
		"Unsigned pure integer version of #GetSamplerParameteriv().",

		GLuint.IN("sampler", "the name of the sampler object from which to retrieve parameters"),
		GLenum.IN("pname", "the symbolic name of a sampler parameter"),
		Check(1)..returnValue..GLuint_p.OUT("params", "the sampler parameters")
	)

	// ARB_texture_rgb10_a2ui

	IntConstant(
		"""
		Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, RenderbufferStorage and
		RenderbufferStorageMultisample.
		""",

		"RGB10_A2UI"..0x906F
	)

	// ARB_texture_swizzle

	IntConstant(
		"Accepted by the {@code pname} parameters of TexParameteri, TexParameterf, TexParameteriv, TexParameterfv, GetTexParameterfv, and GetTexParameteriv.",

		"TEXTURE_SWIZZLE_R"..0x8E42,
		"TEXTURE_SWIZZLE_G"..0x8E43,
		"TEXTURE_SWIZZLE_B"..0x8E44,
		"TEXTURE_SWIZZLE_A"..0x8E45
	)

	IntConstant(
		"Accepted by the {@code pname} parameters of TexParameteriv,  TexParameterfv, GetTexParameterfv, and GetTexParameteriv.",

		"TEXTURE_SWIZZLE_RGBA"..0x8E46
	)

	// ARB_timer_query

	IntConstant(
		"Accepted by the {@code target} parameter of BeginQuery, EndQuery, and GetQueryiv.",

		"TIME_ELAPSED"..0x88BF
	)

	IntConstant(
		"""
		Accepted by the {@code target} parameter of GetQueryiv and QueryCounter. Accepted by the {@code value} parameter of GetBooleanv, GetIntegerv,
		GetInteger64v, GetFloatv, and GetDoublev.
		""",

		"TIMESTAMP"..0x8E28
	)

	void(
		"QueryCounter",
		"Records the GL time into a query object after all previous commands have reached the GL server but have not yet necessarily executed.",

		GLuint.IN("id", "the name of a query object into which to record the GL time"),
		GLenum.IN("target", "the counter to query", "#TIMESTAMP")
	)

	void(
		"GetQueryObjecti64v",
		"Returns the 64bit integer value of query object parameter.",

		GLuint.IN("id", "the name of a query object"),
		GLenum.IN("pname", "the symbolic name of a query object parameter", "GL15#QUERY_RESULT GL15#QUERY_RESULT_AVAILABLE"),
		Check(1)..returnValue..GLint64_p.OUT("params", "the requested data")
	)

	void(
		"GetQueryObjectui64v",
		"Unsigned version of #GetQueryObjecti64v().",

		GLuint.IN("id", "the name of a query object"),
		GLenum.IN("pname", "the symbolic name of a query object parameter"),
		Check(1)..returnValue..GLuint64_p.OUT("params", "the requested data")
	)

	// ARB_instanced_arrays

	IntConstant(
		"Accepted by the {@code pname} parameters of GetVertexAttribdv, GetVertexAttribfv, and GetVertexAttribiv.",

		"VERTEX_ATTRIB_ARRAY_DIVISOR"..0x88FE
	)

	void(
		"VertexAttribDivisor",
		"Modifies the rate at which generic vertex attributes advance during instanced rendering.",

		GLuint.IN("index", "the index of the generic vertex attribute"),
		GLuint.IN("divisor", "the number of instances that will pass between updates of the generic attribute at slot {@code index}")
	)

	// ARB_vertex_type_2_10_10_10_rev

	IntConstant(
		"""
		Accepted by the {@code type} parameter of VertexAttribPointer, VertexPointer, NormalPointer, ColorPointer, SecondaryColorPointer, TexCoordPointer,
		VertexAttribP{1234}ui, VertexP*, TexCoordP*, MultiTexCoordP*, NormalP3ui, ColorP*, SecondaryColorP* and VertexAttribP*.
		""",

		"INT_2_10_10_10_REV"..0x8D9F
	)

	// Packed functions javadoc
	val packedType = "type of packing used on the data"
	val packedTypes = "#INT_2_10_10_10_REV GL12#UNSIGNED_INT_2_10_10_10_REV"
	val packedValue = "the packed value"

	deprecatedGL..void("VertexP2ui", "Packed component version of GL11#Vertex2f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("value", "the packed value"))
	deprecatedGL..void("VertexP3ui", "Packed component version of GL11#Vertex3f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("value", "the packed value"))
	deprecatedGL..void("VertexP4ui", "Packed component version of GL11#Vertex4f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("value", "the packed value"))

	deprecatedGL..void("VertexP2uiv", "Pointer version of #VertexP2ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("value", packedValue))
	deprecatedGL..void("VertexP3uiv", "Pointer version of #VertexP3ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("value", packedValue))
	deprecatedGL..void("VertexP4uiv", "Pointer version of #VertexP4ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("value", packedValue))

	deprecatedGL..void("TexCoordP1ui", "Packed component version of GL11#TexCoord1f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("coords", packedValue))
	deprecatedGL..void("TexCoordP2ui", "Packed component version of GL11#TexCoord2f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("coords", packedValue))
	deprecatedGL..void("TexCoordP3ui", "Packed component version of GL11#TexCoord3f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("coords", packedValue))
	deprecatedGL..void("TexCoordP4ui", "Packed component version of GL11#TexCoord4f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("coords", packedValue))

	deprecatedGL..void("TexCoordP1uiv", "Pointer version of #TexCoordP1ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))
	deprecatedGL..void("TexCoordP2uiv", "Pointer version of #TexCoordP2ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))
	deprecatedGL..void("TexCoordP3uiv", "Pointer version of #TexCoordP3ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))
	deprecatedGL..void("TexCoordP4uiv", "Pointer version of #TexCoordP4ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))

	val texCoordTex = "the coordinate set to be modified"

	deprecatedGL..void("MultiTexCoordP1ui", "Packed component version of GL13#MultiTexCoord1f().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedType), GLuint.IN("coords", packedValue))
	deprecatedGL..void("MultiTexCoordP2ui", "Packed component version of GL13#MultiTexCoord2f().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedType), GLuint.IN("coords", packedValue))
	deprecatedGL..void("MultiTexCoordP3ui", "Packed component version of GL13#MultiTexCoord3f().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedType), GLuint.IN("coords", packedValue))
	deprecatedGL..void("MultiTexCoordP4ui", "Packed component version of GL13#MultiTexCoord4f().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedType), GLuint.IN("coords", packedValue))

	deprecatedGL..void("MultiTexCoordP1uiv", "Pointer version of #MultiTexCoordP1ui().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))
	deprecatedGL..void("MultiTexCoordP2uiv", "Pointer version of #MultiTexCoordP2ui().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))
	deprecatedGL..void("MultiTexCoordP3uiv", "Pointer version of #MultiTexCoordP3ui().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))
	deprecatedGL..void("MultiTexCoordP4uiv", "Pointer version of #MultiTexCoordP4ui().", GLenum.IN("texture", texCoordTex), GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))

	deprecatedGL..void("NormalP3ui", "Packed component version of GL11#Normal3f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("coords", packedValue))

	deprecatedGL..void("NormalP3uiv", "Pointer version #NormalP3ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("coords", packedValue))

	deprecatedGL..void("ColorP3ui", "Packed component version of GL11#Color3f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("color", packedValue))
	deprecatedGL..void("ColorP4ui", "Packed component version of GL11#Color4f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("color", packedValue))

	deprecatedGL..void("ColorP3uiv", "Pointer version of #ColorP3ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("color", packedValue))
	deprecatedGL..void("ColorP4uiv", "Pointer version of #ColorP4ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("color", packedValue))

	deprecatedGL..void("SecondaryColorP3ui", "Packed component version of GL14#SecondaryColor3f().", GLenum.IN("type", packedType, packedTypes), GLuint.IN("color", packedValue))
	deprecatedGL..void("SecondaryColorP3uiv", "Pointer version of #SecondaryColorP3ui().", GLenum.IN("type", packedType, packedTypes), Check(1)..const..GLuint_p.IN("color", packedValue))

	val vertexAttribIndex = "the index of the generic vertex attribute to be modified"
	val vertexAttribNormalized = "whether values should be normalized or cast directly to floating-point"

	void("VertexAttribP1ui", "Packed component version of GL20#VertexAttrib1f().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), GLuint.IN("value", packedValue))
	void("VertexAttribP2ui", "Packed component version of GL20#VertexAttrib2f().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), GLuint.IN("value", packedValue))
	void("VertexAttribP3ui", "Packed component version of GL20#VertexAttrib3f().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), GLuint.IN("value", packedValue))
	void("VertexAttribP4ui", "Packed component version of GL20#VertexAttrib4f().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), GLuint.IN("value", packedValue))

	void("VertexAttribP1uiv", "Pointer version of #VertexAttribP1ui().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), Check(1)..const..GLuint_p.IN("value", packedValue))
	void("VertexAttribP2uiv", "Pointer version of #VertexAttribP2ui().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), Check(1)..const..GLuint_p.IN("value", packedValue))
	void("VertexAttribP3uiv", "Pointer version of #VertexAttribP3ui().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), Check(1)..const..GLuint_p.IN("value", packedValue))
	void("VertexAttribP4uiv", "Pointer version of #VertexAttribP4ui().", GLuint.IN("index", vertexAttribIndex), GLenum.IN("type", packedType, packedTypes), GLboolean.IN("normalized", vertexAttribNormalized), Check(1)..const..GLuint_p.IN("value", packedValue))

}