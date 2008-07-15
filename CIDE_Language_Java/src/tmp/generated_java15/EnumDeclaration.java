package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumDeclaration extends GenASTNode {
  public EnumDeclaration(ASTStringNode identifier, ImplementsList implementsList, EnumBody enumBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<ImplementsList>("implementsList", implementsList),
      new PropertyOne<EnumBody>("enumBody", enumBody)
    }, firstToken, lastToken);
  }
  public EnumDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ImplementsList getImplementsList() {
    return ((PropertyZeroOrOne<ImplementsList>)getProperty("implementsList")).getValue();
  }
  public EnumBody getEnumBody() {
    return ((PropertyOne<EnumBody>)getProperty("enumBody")).getValue();
  }
}
