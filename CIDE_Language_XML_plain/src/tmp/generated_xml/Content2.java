package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content2 extends Content {
  public Content2(Comment comment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Comment>("comment", comment)
    }, firstToken, lastToken);
  }
  public Content2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Content2(cloneProperties(),firstToken,lastToken);
  }
  public Comment getComment() {
    return ((PropertyOne<Comment>)getProperty("comment")).getValue();
  }
}
