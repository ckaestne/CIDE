package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class file_input extends GenASTNode implements ISourceFile {
  public file_input(ArrayList<newlineOrStmt> newlineOrStmt, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<newlineOrStmt>("newlineOrStmt", newlineOrStmt),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public file_input(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new file_input(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<newlineOrStmt> getNewlineOrStmt() {
    return ((PropertyZeroOrMore<newlineOrStmt>)getProperty("newlineOrStmt")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
