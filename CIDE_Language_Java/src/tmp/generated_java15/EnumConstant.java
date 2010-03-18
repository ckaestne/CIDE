package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumConstant extends GenASTNode {
  public EnumConstant(ASTStringNode identifier, Arguments arguments, ClassOrInterfaceBody classOrInterfaceBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<Arguments>("arguments", arguments),
      new PropertyZeroOrOne<ClassOrInterfaceBody>("classOrInterfaceBody", classOrInterfaceBody)
    }, firstToken, lastToken);
  }
  public EnumConstant(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumConstant(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public Arguments getArguments() {
    return ((PropertyZeroOrOne<Arguments>)getProperty("arguments")).getValue();
  }
  public ClassOrInterfaceBody getClassOrInterfaceBody() {
    return ((PropertyZeroOrOne<ClassOrInterfaceBody>)getProperty("classOrInterfaceBody")).getValue();
  }
}
