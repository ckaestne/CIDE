package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ElementDecl extends GenASTNode {
  public ElementDecl(ASTStringNode name, ContentSpec contentSpec, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("name", name),
      new PropertyOne<ContentSpec>("contentSpec", contentSpec)
    }, firstToken, lastToken);
  }
  public ElementDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new ElementDecl(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getName() {
    return ((PropertyOne<ASTStringNode>)getProperty("name")).getValue();
  }
  public ContentSpec getContentSpec() {
    return ((PropertyOne<ContentSpec>)getProperty("contentSpec")).getValue();
  }
}
