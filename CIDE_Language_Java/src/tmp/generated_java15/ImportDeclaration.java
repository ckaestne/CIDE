package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ImportDeclaration extends GenASTNode {
  public ImportDeclaration(ASTTextNode text373, Name name, ASTTextNode text374, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text373", text373),
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<ASTTextNode>("text374", text374)
    }, firstToken, lastToken);
  }
  public ImportDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ImportDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText373() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text373")).getValue();
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public ASTTextNode getText374() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text374")).getValue();
  }
}
