package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice21 extends Content_head_Choice2 {
  public Content_head_Choice21(Content_head_Seq2 content_head_Seq2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Content_head_Seq2>("content_head_Seq2", content_head_Seq2)
    }, firstToken, lastToken);
  }
  public Content_head_Choice21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice21(cloneProperties(),firstToken,lastToken);
  }
  public Content_head_Seq2 getContent_head_Seq2() {
    return ((PropertyOne<Content_head_Seq2>)getProperty("content_head_Seq2")).getValue();
  }
}
