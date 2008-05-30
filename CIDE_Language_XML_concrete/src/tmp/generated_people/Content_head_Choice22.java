package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice22 extends Content_head_Choice2 {
  public Content_head_Choice22(Content_head_Seq4 content_head_Seq4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Content_head_Seq4>("content_head_Seq4", content_head_Seq4)
    }, firstToken, lastToken);
  }
  public Content_head_Choice22(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice22(cloneProperties(),firstToken,lastToken);
  }
  public Content_head_Seq4 getContent_head_Seq4() {
    return ((PropertyOne<Content_head_Seq4>)getProperty("content_head_Seq4")).getValue();
  }
}
