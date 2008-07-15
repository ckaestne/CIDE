package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeParameter extends GenASTNode {
  public TypeParameter(ASTStringNode identifier, TypeBound typeBound, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<TypeBound>("typeBound", typeBound)
    }, firstToken, lastToken);
  }
  public TypeParameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeParameter(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public TypeBound getTypeBound() {
    return ((PropertyZeroOrOne<TypeBound>)getProperty("typeBound")).getValue();
  }
}
