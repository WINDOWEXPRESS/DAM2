#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
<<<<<<< HEAD
#include "GeneratorMD5.c"

#define ASCII_a 97
#define ASCII_z 122
#define LONGITUD_CHAR 5

int main(int arc, char *argv[])
{
=======

#define MD5_LEN 16

void generateMD5(const char *string, unsigned char *str_result) {
    EVP_MD_CTX *ctx;
    const EVP_MD *md;
    unsigned char result[EVP_MAX_MD_SIZE];

    ctx = EVP_MD_CTX_new();
    md = EVP_md5();

    EVP_DigestInit_ex(ctx, md, NULL);
    EVP_DigestUpdate(ctx, string, strlen(string));
    EVP_DigestFinal_ex(ctx, result, NULL);

    EVP_MD_CTX_free(ctx);

    for(int i = 0; i < MD5_LEN; i++){   // MD5 result is always 16 bytes
        sprintf(str_result+(i*2),"%02x", result[i]);
    }
}

int main(int arc, char *argv[]) {
>>>>>>> 1cdc42886e8dcd99eaf888d69c0f1094ea7aca5c
    char *string = argv[1];

    unsigned char result[EVP_MAX_MD_SIZE];
    unsigned int result_len;

    generateMD5(string, result);

<<<<<<< HEAD
    // Demostrar el md5 de argumento
    // printf("MD5(\"%s\") = %s", string, result);

    char password[LONGITUD_CHAR];
    char *md5[] = {"582fc884d6299814fbd4f12c1f9ae70f",
                   "74437fabd7c8e8fd178ae89acbe446f2",
                   "28ea19352381b8659df830dd6d5c90a3",
                   "90f077d7759d0d4d21e6867727d4b2bd"};
    char caracter;

    for (char i = ASCII_a; i <= ASCII_z; i++)
    {
        password[0] = i;
        for (char j = ASCII_a; j <= ASCII_z; j++)
        {
            password[1] = j;
            for (char k = ASCII_a; k <= ASCII_z; k++)
            {
                password[2] = k;
                for (char l = ASCII_a; l <= ASCII_z; l++)
                {
                    password[3] = l;
                    // printf("%s\n", password);
                    generateMD5(password, result);

                    for (int i = 0; i < sizeof(md5)/sizeof(md5[0]); i++)
                    {
                        if (0 == strcmp(result, md5[i]))
                        {
                            printf("%s MD5: %s \n", password, result);
                        }
                    }
                }
            }
        }
    }

    printf("\n");
=======
    printf("MD5(\"%s\") = %s", string, result);
   
    printf("\n");

>>>>>>> 1cdc42886e8dcd99eaf888d69c0f1094ea7aca5c
    return 0;
}