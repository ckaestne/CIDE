package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceTypeIntern extends GenASTNode {
  public ClassOrInterfaceTypeIntern(ASTStringNode identifier, TypeArguments typeArguments, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<TypeArguments>("typeArguments", typeArguments)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceTypeIntern(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceTypeIntern(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public TypeArguments getTypeArguments() {
    return ((PropertyZeroOrOne<TypeArguments>)getProperty("typeArguments")).getValue();
  }
}
