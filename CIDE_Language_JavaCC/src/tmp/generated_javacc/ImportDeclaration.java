package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ImportDeclaration extends GenASTNode {
  public ImportDeclaration(ASTTextNode text480, Name name, ASTTextNode text481, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text480", text480),
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<ASTTextNode>("text481", text481)
    }, firstToken, lastToken);
  }
  public ImportDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new ImportDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText480() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text480")).getValue();
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public ASTTextNode getText481() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text481")).getValue();
  }
}
