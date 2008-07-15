package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element3 extends Element {
  public Element3(CommentTag commentTag, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CommentTag>("commentTag", commentTag)
    }, firstToken, lastToken);
  }
  public Element3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element3(cloneProperties(),firstToken,lastToken);
  }
  public CommentTag getCommentTag() {
    return ((PropertyOne<CommentTag>)getProperty("commentTag")).getValue();
  }
}
