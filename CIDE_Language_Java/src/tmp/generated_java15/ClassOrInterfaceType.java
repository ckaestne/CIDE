package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceType extends GenASTNode {
  public ClassOrInterfaceType(ASTStringNode identifier, TypeArguments typeArguments, ArrayList<ClassOrInterfaceTypeIntern> classOrInterfaceTypeIntern, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<TypeArguments>("typeArguments", typeArguments),
      new PropertyZeroOrMore<ClassOrInterfaceTypeIntern>("classOrInterfaceTypeIntern", classOrInterfaceTypeIntern)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceType(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceType(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public TypeArguments getTypeArguments() {
    return ((PropertyZeroOrOne<TypeArguments>)getProperty("typeArguments")).getValue();
  }
  public ArrayList<ClassOrInterfaceTypeIntern> getClassOrInterfaceTypeIntern() {
    return ((PropertyZeroOrMore<ClassOrInterfaceTypeIntern>)getProperty("classOrInterfaceTypeIntern")).getValue();
  }
}
