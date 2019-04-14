package com.acme.videoserver.webapp.modules;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonStructure;

import org.takes.rs.RsJson;

import com.acme.videoserver.core.image.Base64Encoded;
import com.acme.videoserver.core.library.Result;
import com.acme.videoserver.core.library.Videoclip;

/**
 * FIXME this is definitely not nice, because we shall use printers here instead of "getters"...
 * 
 * @author Zsolt
 */
public class JsonVideoclipResult implements RsJson.Source {

	private final Result<Videoclip> result;

	public JsonVideoclipResult(Result<Videoclip> result) {
		this.result = result;
	}

	public JsonStructure toJson() throws IOException {
		
		JsonArrayBuilder values = Json.createArrayBuilder();
		
		for (Videoclip clip  : result.result()) {
			values.add(
				Json.createObjectBuilder().add("uuid", clip.uuid())
						  .add("title", clip.title())
						  .add("thumbnail", new Base64Encoded(clip.thumbnail()).asString())
						  .add("recordDateTime", clip.recordingDateTime().toString())
						  .add("description", clip.description())
						  .add("participants", Json.createArrayBuilder(clip.participants()))
						  .add("tags", Json.createArrayBuilder(clip.tags()))
			);
		}
		
		return Json.createObjectBuilder()
				.add("total", result.total())
				.add("values", values)
				.build();
	}

}
