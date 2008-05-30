package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AttListDecl extends GenASTNode {
  public AttListDecl(ASTStringNode name, ArrayList<AttribDef> attribDef, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("name", name),
      new PropertyZeroOrMore<AttribDef>("attribDef", attribDef)
    }, firstToken, lastToken);
  }
  public AttListDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new AttListDecl(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getName() {
    return ((PropertyOne<ASTStringNode>)getProperty("name")).getValue();
  }
  public ArrayList<AttribDef> getAttribDef() {
    return ((PropertyZeroOrMore<AttribDef>)getProperty("attribDef")).getValue();
  }
}
