package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ReferenceTypeP1 extends ReferenceTypeP {
  public ReferenceTypeP1(PrimitiveType primitiveType, ArrayList<ASTTextNode> text396, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimitiveType>("primitiveType", primitiveType),
      new PropertyOneOrMore<ASTTextNode>("text396", text396)
    }, firstToken, lastToken);
  }
  public ReferenceTypeP1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new ReferenceTypeP1(cloneProperties(),firstToken,lastToken);
  }
  public PrimitiveType getPrimitiveType() {
    return ((PropertyOne<PrimitiveType>)getProperty("primitiveType")).getValue();
  }
  public ArrayList<ASTTextNode> getText396() {
    return ((PropertyOneOrMore<ASTTextNode>)getProperty("text396")).getValue();
  }
}
