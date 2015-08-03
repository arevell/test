package com.ttc.ch2.ui.moduls.details.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.ecs.html.Span;

import com.google.common.base.Preconditions;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;

public class TdBrandDetailsDecorator extends BaseCommentDecorator {

	public static final String name = "tdbrandsdetails";

	@Override
	public String decorateContent(Comment comment) {
		Preconditions.checkArgument(comment!=null,"TdBrandDetailsDecorator->decorateContent comment is null");
		return this.decorateContent(comment.getContent());
	}

	@Override
	public String decorateContent(String txt) {
		String content = super.decorateContent(txt);

		content = content.replace("Brands information:", "<b>Brands information:</b>");
		content = content.replace("Details sellingCompany:", "<b>Details sellingCompany:</b>");
		content = content.replace("Product Details:", "<b>Product Details:</b>");

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line = "";
		boolean startError = false;
		boolean startWarning = false;
		
		try {
			
			while ((line = reader.readLine()) != null) {
				
				String result = null;

				if (line.trim().startsWith("ERR-")) {
					startError = true;
					startWarning = false;
				} else if (line.trim().startsWith("WRN-")) {
					startError = false;
					startWarning = true;
				} else if (line.trim().startsWith("INF-") || line.trim().contains("File name:")) {
					startError = false;
					startWarning = false;
				}else if (line.trim().contains("Brands information:")) {
					startError = false;
					startWarning = false;
				}
				else if (line.trim().contains("Details sellingCompany:")) {
					startError = false;
					startWarning = false;
				}
				else if (line.trim().contains("Product Details:")) {
					startError = false;
					startWarning = false;
				}

				if (startError == true) {
					Span span = new Span();
					span.setTagText(line);
					span.addAttribute("class", "error_bold");
					result = span.toString();
				} else if (startWarning == true) {
					Span span = new Span();
					span.setTagText(line);
					span.addAttribute("class", "warning_bold");
					result = span.toString();
				} else if (line.contains("SellingCompany:")) {
					Span span = new Span();
					span.setTagText(line);
					span.addAttribute("class", "hightlight");
					result = span.toString();
				} else if (line.contains("Imported companies information:")) {
					Span span = new Span();
					span.setTagText(line);
					span.addAttribute("class", "hightlight");
					result = span.toString();
				} else if (line.contains("Product:")) {
					Span span = new Span();
					span.setTagText(line);
					span.addAttribute("class", "hightlight");
					result = span.toString();
				} else {
					result = line;
				}

				sb.append(result).append(END_LINE);
			}

		} catch (IOException e) {
			throw new CH2Exception(e);
		}

		return sb.toString();
	}
}
