package com.msa.fiveio.ai.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeminiRequestDto {

	private List<Content> contents;

	public GeminiRequestDto(String text) {
		createGeminiReqDto(text);
	}

	@Data
	public static class Content {

		private List<Part> parts;

		@JsonCreator
		public Content(@JsonProperty("text") String text) {
			parts = new ArrayList<>();
			parts.add(new Part(text));
		}

		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Part {

			@JsonProperty("text")
			private String text;
		}
	}

	public void createGeminiReqDto(String text) {
		this.contents = new ArrayList<>();
		this.contents.add(new Content(text));
	}
}
