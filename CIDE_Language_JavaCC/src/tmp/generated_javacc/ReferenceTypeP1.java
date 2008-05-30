package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ReferenceTypeP1 extends ReferenceTypeP {
  public ReferenceTypeP1(PrimitiveType primitiveType, ArrayList<ASTTextNode> text504, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimitiveType>("primitiveType", primitiveType),
      new PropertyOneOrMore<ASTTextNode>("text504", text504)
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
  public ArrayList<ASTTextNode> getText504() {
    return ((PropertyOneOrMore<ASTTextNode>)getProperty("text504")).getValue();
  }
}
