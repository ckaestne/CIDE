package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ContentSpec3 extends ContentSpec {
  public ContentSpec3(Children children, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Children>("children", children)
    }, firstToken, lastToken);
  }
  public ContentSpec3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new ContentSpec3(cloneProperties(),firstToken,lastToken);
  }
  public Children getChildren() {
    return ((PropertyOne<Children>)getProperty("children")).getValue();
  }
}
