package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Grammar extends GenASTNode implements ISourceFile {
  public Grammar(ASTStringNode findintroductionblock, ArrayList<Production> production, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findintroductionblock", findintroductionblock),
      new PropertyZeroOrMore<Production>("production", production),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public Grammar(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Grammar(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindintroductionblock() {
    return ((PropertyOne<ASTStringNode>)getProperty("findintroductionblock")).getValue();
  }
  public ArrayList<Production> getProduction() {
    return ((PropertyZeroOrMore<Production>)getProperty("production")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
