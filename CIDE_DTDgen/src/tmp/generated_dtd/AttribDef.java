package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AttribDef extends GenASTNode {
  public AttribDef(ASTStringNode name, AttribType attribType, DefaultDecl defaultDecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("name", name),
      new PropertyOne<AttribType>("attribType", attribType),
      new PropertyOne<DefaultDecl>("defaultDecl", defaultDecl)
    }, firstToken, lastToken);
  }
  public AttribDef(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new AttribDef(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getName() {
    return ((PropertyOne<ASTStringNode>)getProperty("name")).getValue();
  }
  public AttribType getAttribType() {
    return ((PropertyOne<AttribType>)getProperty("attribType")).getValue();
  }
  public DefaultDecl getDefaultDecl() {
    return ((PropertyOne<DefaultDecl>)getProperty("defaultDecl")).getValue();
  }
}
